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
    private int score = 0;
    private boolean mandatory = true;

    public Restriction(String name) {
        this.name = name;
        this.restriccion = new ArrayList<>();
        this.args = new ArrayList<>();
        setScore(score);
        setMandatory(mandatory);
    }

    public boolean add(String blockName) {

        // Get block
        if (Blocks.getInstance().exists(blockName)) {
            Function<In, Out> b = Blocks.getInstance().get(blockName);
            restriccion.add(b);
            this.args.add(new Object[]{});
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

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score >= 0) {
            this.score = score;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean setParameter(int blockIndex, Object[] args) {
        return this.args.set(blockIndex, args) == args;
    }

    public boolean setParameters(ArrayList<Object[]> args) {
        this.args = args;
        return true;
    }
}


