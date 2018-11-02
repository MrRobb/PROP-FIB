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

    boolean exists(Subject subj) {
        return subjects.containsValue(subj);
    }

    Integer addSubject(Subject subj) {
        if (subj == null) {
            return invalidID;
        }
        else {
            Integer id = subjects.size();
            subjects.put(id, subj);
            return id;
        }
    }

    Subject get(Integer id) {
        return subjects.get(id);
    }

    Integer getID(Subject subj) {
        for (Map.Entry<Integer, Subject> value : subjects.entrySet()) {
            if (value.getValue().equals(subj)) {
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
