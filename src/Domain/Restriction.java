package Domain;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

public class Restriction {

    private String name;
    private ArrayList<Function<In, Out>> restriccion;
    private ArrayList<Object[]> args;

    public Restriction() {

    }

    public boolean add(String blockName, Object[] args) {

        // Get block
        if (Blocks.getInstance().exists(blockName)) {
            Function<In, Out> b = Blocks.getInstance().get(blockName);
            restriccion.add(b);
            this.args.add(args);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean apply(In input) {

        try {

            Out output = new Out(Boolean.FALSE);
            int i = 0;

            for (Function<In, Out> block : restriccion) {
                output = block.apply(input);
                input = new In(output.get(), args.get(i));
            }

            return (boolean) output.get(0);
        }

        catch (Exception e) {
            System.out.println("Checking restriction failed!");
            return false;
        }
    }

}


