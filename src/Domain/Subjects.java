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

    public String addSubject(Subject subj) {
        if (subj == null) {
            return invalidID;
        }
        else {
            String id = subj.getName();
            subjects.put(id, subj);
            return id;
        }
    }

    public Subject get(String id) {
        return subjects.get(id);
    }

    public String getID(Subject subj) {
        for (Map.Entry<String, Subject> value : subjects.entrySet()) {
            if (value.getValue().equals(subj)) {
                return value.getKey();
            }
        }
        return Subjects.invalidID;
    }

    public String getIDfromName(String nameSubj){
        for (Map.Entry<String, Subject> value : subjects.entrySet()) {
            if (value.getValue().getName().equals(nameSubj)) {
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
