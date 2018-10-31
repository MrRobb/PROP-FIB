package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Degrees {

    public static Integer invalidID = -1;
    private static Degrees instance = null;

    private HashMap<Integer, Degree> degrees;

    private Degrees() {
        degrees = new HashMap<Integer, Degree>(0);
    }

    static Degrees getInstance() {
        if (instance == null) {
            instance = new Degrees();
        }

        return instance;
    }

    boolean exists(Integer id) {
        return degrees.containsKey(id);
    }

    boolean exists(Degree degree) {
        return degrees.containsValue(degree);
    }

    Integer addDegree(Degree degree) {
        if (degree == null) {
            return invalidID;
        }
        else {
            Integer id = degrees.size();
            degrees.put(id, degree);
            return id;
        }
    }

    Degree get(Integer id) {
        return degrees.get(id);
    }

    Integer getID(Degree degree) {
        for (Map.Entry<Integer, Degree> value : degrees.entrySet()) {
            if (value.getValue().equals(degree)) {
                return value.getKey();
            }
        }
        return invalidID;
    }

    Integer getNewID() {
        return degrees.size();
    }

    Integer size() {
        return degrees.size();
    }

    boolean clear() {
        Degrees.getInstance().degrees.clear();
        return true;
    }


}
