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

    public static Subjects getInstance() {
        if (instance == null) {
            instance = new Subjects();
        }

        return instance;
    }

    public boolean exists(Integer id) {
        return subjects.containsKey(id);
    }

    public boolean exists(Subject subj) {
        return subjects.containsValue(subj);
    }

    public Integer addSubject(Subject subj) {
        if (subj == null) {
            return invalidID;
        }
        else {
            Integer id = subjects.size();
            subjects.put(id, subj);
            return id;
        }
    }

    public Subject get(Integer id) {
        return subjects.get(id);
    }

    public Integer getID(Subject subj) {
        for (Map.Entry<Integer, Subject> value : subjects.entrySet()) {
            if (value.getValue().equals(subj)) {
                return value.getKey();
            }
        }
        return Subjects.invalidID;
    }

    public Integer getNewID() {
        return subjects.size();
    }

    public Integer size() {
        return subjects.size();
    }

    public boolean clear() {
        Subjects.getInstance().subjects.clear();
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
