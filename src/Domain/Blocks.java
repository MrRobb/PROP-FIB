package Domain;

import java.util.HashMap;
import java.util.function.Function;

public class Blocks {

    private static HashMap<String, Function<In, Out>> blocks;

    /**
     * Constructor / Destructor
     */

    Blocks(HashMap<String, Function<In, Out>> blocks) {
        this.blocks = blocks;
    }

    /**
     * Getter / Setter
     */

    public static HashMap<String, Function<In, Out>> getBlocks() {
        return blocks;
    }

    public static Boolean setBlocks(HashMap<String, Function<In, Out>> b) {
        blocks = b;
        return true;
    }

    /**
     * Modifier
     */

    public static Boolean addBlock(String s, Function<In, Out> b) {
        if (blocks.get(s) != null) return false;
        blocks.put(s,b);
        return true;
    }


    /**
     * Consultant
     */

    public static Function<In, Out> getBlock(String s) {
        return blocks.get(s);
    }

    public static Boolean existsBlock(String s) {
        if (blocks.get(s) != null) return true;
        else return false;
    }

}
