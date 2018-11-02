package Domain;

import java.util.HashMap;
import java.util.function.Function;

public class Blocks {

    private static HashMap<String, Function<In, Out>> blocks = createBlock();

    private static HashMap<String, Function<In, Out>> createBlock() {
        HashMap<String, Function<In, Out>> b = new HashMap<String, Function<In, Out>>();
        blocks.put("No class Between", Block::noClassOnDayBetweenSHEH);
        blocks.put("No overlapping classroom and time", Block::noTwoClassesAtSameHourSameClassroom);
        return b;
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
