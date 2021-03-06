package Domain;

import java.util.HashMap;
import java.util.Set;

class Blocks {

    public static String invalidID = "";
    private static Blocks instance = null;

    private HashMap<String, Block> blocks;

    private Blocks() {
        blocks = new HashMap<>(0);
    }

    public static Blocks getInstance() {
        if (instance == null) {
            instance = new Blocks();
        }

        return instance;
    }

    /**
     * Getters / Setters
     */

    public Set<String> getNames() {
        return blocks.keySet();
    }

    /**
     * Modifiers
     */

    public boolean add(String id, Block block) {
        if (blocks.containsKey(id)) {
            return false;
        }

        blocks.put(id, block);
        return true;
    }

    public boolean delete(String id) {
        if (exists(id)) {
            blocks.remove(id);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Consultants
     */

    public Block get(String id) {
        return blocks.get(id);
    }

    public boolean exists(String id) {
        return blocks.containsKey(id);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
