package Domain;

import javafx.util.Pair;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

public class Restriction {

    private String name;
    private ArrayList<Block> restriccion;
    private ArrayList<Object[]> args;
    private int score = 0;
    private boolean mandatory = true;

    public Restriction(String name) {
        this.name = name;
        this.restriccion = new ArrayList<>();
        this.args = new ArrayList<>();
    }

    public Restriction(String name, ArrayList<Block> restriccion, ArrayList<Object[]> args, int score, boolean mandatory) {
        this.name = name;
        this.restriccion = restriccion;
        this.args = args;
        this.score = score;
        this.mandatory = mandatory;
    }

    public boolean add(String blockName) {

        // Get block
        if (Blocks.getInstance().exists(blockName)) {
            Block b = Blocks.getInstance().get(blockName);
            restriccion.add(b);
            this.args.add(new Object[]{});
            return true;
        }
        else {
            return false;
        }
    }

    public String getParams() {
        StringBuilder s = new StringBuilder();
        for (Object[] objs : args) {
            for (Object o : objs) {
                s.append(o);
                s.append(" ");
            }
        }
        return s.toString();
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

    public boolean checkParam(int blockIndex, int paramIndex, Object arg1) {
        Block b = restriccion.get(blockIndex);
        if (b == null) return false;
        Pair<String, java.lang.Class> arg2 = b.getArgs().get(paramIndex);
        java.lang.Class c1 = arg2.getValue();
        java.lang.Class c2 = arg1.getClass();
        return c1 == c2;
    }

    public boolean setParameter(int blockIndex, Object[] args) {
        Block b = restriccion.get(blockIndex);
        if (b == null) return false;
        if (args.length != b.getArgs().size()) return false;
        int i = 0;
        for (Pair<String, java.lang.Class> arg : b.getArgs()) {
            java.lang.Class c1 = arg.getValue();
            java.lang.Class c2 = args[i].getClass();
            if (c1 != c2) return false;
            i++;
        }
        return this.args.set(blockIndex, args) == args;
    }

    public String askParameters(int blockIndex) {
        Block b = restriccion.get(blockIndex);
        return  b.toStringArgs();
    }

    public String askParameter(int blockIndex, int i) {
        Block b = restriccion.get(blockIndex);
        return b.toStringArg(i);
    }

    public boolean setParameters(ArrayList<Object[]> args) {
        this.args = args;
        return true;
    }

    public int getNumberOfBlocks() {
        return restriccion.size();
    }

    public int getSizeArgsBlock(int blockIndex) {
        Block b = restriccion.get(blockIndex);
        return b.argSize();
    }

    @Override
    protected Restriction clone() {
        return new Restriction(this.name, this.restriccion, this.args, this.score, this.mandatory);
    }
}


