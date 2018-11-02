package Domain;

import java.util.HashMap;
import java.util.Map;

public class Subjects {

    public static Integer invalidID = -1;
    private static Subjects instance = null;

    private HashMap<Integer, Subject> subjects;

    private Subjects() {
        subjects = new HashMap<Integer, Subject>(0);
    }

    static Subjects getInstance() {
        if (instance == null) {
            instance = new Subjects();
        }

        return instance;
    }

    boolean exists(Integer id) {
        return subjects.containsKey(id);
    }

    boolean exists(Subject datetime) {
        return subjects.containsValue(datetime);
    }

    Integer addSubject(Subject datetime) {
        if (datetime == null) {
            return invalidID;
        }
        else {
            Integer id = subjects.size();
            subjects.put(id, datetime);
            return id;
        }
    }

    Subject get(Integer id) {
        return subjects.get(id);
    }

    Integer getID(Subject datetime) {
        for (Map.Entry<Integer, Subject> value : subjects.entrySet()) {
            if (value.getValue().equals(datetime)) {
                return value.getKey();
            }
        }
        return Subjects.invalidID;
    }

    Integer getNewID() {
        return subjects.size();
    }

    Integer size() {
        return subjects.size();
    }

    boolean clear() {
        Subjects.getInstance().subjects.clear();
        return true;
    }

}
