package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Degrees {

    public static String invalidID = "";
    private static Degrees instance = null;

    private HashMap<String, Degree> degrees;

    private Degrees() {
        degrees = new HashMap<String, Degree>(0);
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

    String addDegree(Degree degree) {
        if (degree == null) {
            return invalidID;
        }
        else {
            String id = degree.getName();
            degrees.put(id, degree);
            return id;
        }
    }

    Degree get(String id) {
        return degrees.get(id);
    }

    String getID(Degree degree) {
        for (Map.Entry<String, Degree> value : degrees.entrySet()) {
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
