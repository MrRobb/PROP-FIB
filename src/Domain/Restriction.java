package Domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Restriction {

    private ArrayList<Function<Object[], Object[]>> restriccion;
    

    Restriction() {

        //blocks.put("No class on day...", this::noClassOnDayBetweenSHEH);

    }

    boolean AddBlock(String blockName) {

        // Get block
        Function<Object[], Object[]> func = blocks.get(blockName);

        // Add block
        restriccion.add(func);

        return true;
    }

    Boolean apply(Object[] o) {

        Object[] ret = o;

        try {
            for (Function<Object[], Object[]> block : restriccion) {
                ret = block.apply(ret);
            }

        }
        catch (Exception e) {

        }

        return (Boolean) ret[0];
    }

}


