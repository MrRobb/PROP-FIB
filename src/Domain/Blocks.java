package Domain;

import java.util.HashMap;
import java.util.function.Function;

public class Blocks {

    HashMap<String, Block> blocks;

    /**
     * Constructor / Destructor
     */

    Blocks(HashMap<String, Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Getter / Setter
     */

    public HashMap<String, Block> getBlocks() {
        return blocks;
    }

    public Boolean setBlocks(HashMap<String, Block> blocks) {
        this.blocks = blocks;
        return true;
    }

    /**
     * Modifier
     */

    public Boolean addBlock(String s, Block b) {
        if (blocks.get(s) != null) return false;
        blocks.put(s,b);
        return true;
    }


    /**
     * Consultant
     */

    public Boolean existsBlock(String s) {
        if (blocks.get(s) != null) return true;
        else return false;
    }

}
