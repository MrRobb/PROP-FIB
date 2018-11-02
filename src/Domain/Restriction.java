package Domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class Restriction {

    private ArrayList<Function<In, Out>> restriccion;
    private String name;
    private ArrayList<Object> args;
    

    Restriction() {

        //blocks.put("No class on day...", this::noClassOnDayBetweenSHEH);

    }

    boolean AddBlock(String blockName) {

        // Get block
        if (Blocks.existsBlock(blockName)) {
            Function<In, Out> b = Blocks.getBlock(blockName);
            restriccion.add(b);
            return true;
        }
        else {
            return false;
        }
    }

    Boolean apply(Object[] o) {

        Object[] ret = o;

        try {
            for (Function<In, Out> block : restriccion) {
                ret = block.apply(ret);
            }

        }
        catch (Exception e) {

        }

        return (Boolean) ret[0];
    }

}


