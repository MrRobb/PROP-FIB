package Domain;

import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
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
        boolean first = true;
        for (Object[] objs : args) {
            for (Object o : objs) {
                if (!first) {
                    s.append(' ');
                }
                s.append(o);
                first = false;
            }
        }
        return s.toString();
    }

    public boolean apply(In input) {

        try {

            Out output = new Out(Boolean.TRUE);
            int i = 0;

            for (Block block : restriccion) {
                input.setArgs(args.get(i));
                output = block.apply(input);
                input.setInput(output);
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

    public boolean checkParam(int blockIndex, int paramIndex, Object argGiven) {

        Block b = restriccion.get(blockIndex);
        if (b == null) return false;

        java.lang.Class<?> c = b.getArgs().get(paramIndex).getValue();

        try {
            if (c != argGiven.getClass()) {
                c.getMethod("valueOf", new java.lang.Class[]{String.class}).invoke(null, (String)argGiven);
            }
            return true;
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }

    public Object castParam(int blockIndex, int paramIndex, Object argGiven) {

        Block b = restriccion.get(blockIndex);
        if (b == null) return false;

        java.lang.Class<?> c = b.getArgs().get(paramIndex).getValue();

        try {
            if (c == argGiven.getClass()) {
                return argGiven;
            }
            else {
                return c.getMethod("valueOf", new java.lang.Class[]{String.class}).invoke(null, (String)argGiven);
            }
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public boolean setParameter(int blockIndex, Object[] args) {

        Block b = restriccion.get(blockIndex);

        if (b == null) {
            return false;
        }

        if (args.length != b.getArgs().size()) {
            return false;
        }

        for (int argIndex = 0; argIndex < b.getArgs().size(); argIndex++) {
            if (!checkParam(blockIndex, argIndex, args[argIndex])) {
                return false;
            }

            args[argIndex] = castParam(blockIndex, argIndex, args[argIndex]);
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

    public int getNumberOfTotalParams() {
        int sum = 0;
        for (Block block : restriccion) {
            sum += block.argSize();
        }
        return sum;
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
        return new Restriction(
                this.name,
                (ArrayList<Block>) this.restriccion.clone(),
                (ArrayList<Object[]>) this.args.clone(),
                this.score,
                this.mandatory
        );
    }
}


