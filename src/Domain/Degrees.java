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

    public static Degrees getInstance() {
        if (instance == null) {
            instance = new Degrees();
        }

        return instance;
    }

    public boolean exists(Integer id) {
        return degrees.containsKey(id);
    }

    public boolean exists(Degree degree) {
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

    public Integer getNewID() {
        return degrees.size();
    }

    public Integer size() {
        return degrees.size();
    }

    public boolean clear() {
        Degrees.getInstance().degrees.clear();
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
