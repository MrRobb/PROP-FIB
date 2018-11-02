package Domain;

import java.util.HashMap;
import java.util.Map;

public class Subjects {

    public static String invalidID = "";
    private static Subjects instance = null;

    private HashMap<String, Subject> subjects;

    private Subjects() {
        subjects = new HashMap<String, Subject>(0);
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

    String addSubject(Subject subj) {
        if (subj == null) {
            return invalidID;
        }
        else {
            String id = subj.getName();
            subjects.put(id, subj);
            return id;
        }
    }

    Subject get(String id) {
        return subjects.get(id);
    }

    String getID(Subject subj) {
        for (Map.Entry<String, Subject> value : subjects.entrySet()) {
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
