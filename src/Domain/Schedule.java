package Domain;

import apple.laf.JRSUIUtils;
import javafx.util.Pair;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

public class Schedule implements Comparable {

    public ArrayList<Class> getClasses;
    /**
     * Attributes
     */

    ArrayList<Class> classes;
    Integer score = null;
    LinkedHashMap<Pair<Integer, String>, Integer> schedule = null;
    TreeSet<Pair<Integer, String>> keys = null;

    /**
     * Constructors / Destructors
     */

    public Schedule(Set<Integer> dates, Set<String> classrooms) {
        classes = new ArrayList<>(0);

        keys = new TreeSet<>();
        for (String classroom : classrooms) {
            for (Integer date : dates) {
                keys.add(new Pair<>(date, classroom));
            }
        }

        schedule = new LinkedHashMap<>(Groups.getInstance().size());
    }


    public ArrayList<Class> getClasses() {
        return classes;
    }

    /**
     * Getters / Setters
     */

    /**
     * Consultants
     */
    public boolean hasClass(Class c) {
        return classes.contains(c);
    }

    public ArrayList<Class> filterClassroom(Integer classroomID) {

        ArrayList<Class> result = new ArrayList<>(0);

        for (Class c : classes) {
            if (c.getClassroomID().equals(classroomID)) {
                result.add(c);
            }
        }

        return result;
    }

    public ArrayList<Class> filterDateTime(Integer datetimeID) {

        ArrayList<Class> result = new ArrayList<>(0);

        for (Class c : classes) {
            if (c.getDateTimeID().equals(datetimeID)) {
                result.add(c);
            }
        }

        return result;
    }

    public ArrayList<Class> filterGroup(Integer groupID) {

        ArrayList<Class> result = new ArrayList<>(0);

        for (Class c : classes) {
            if (c.getGroupID().equals(groupID)) {
                result.add(c);
            }
        }

        return result;
    }

    public boolean hasClassroom(Integer classroomID) {

        for (Class c : classes) {
            if (c.getClassroomID().equals(classroomID)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasDateTime(Integer datetimeID) {

        for (Class c : classes) {
            if (c.getDateTimeID().equals(datetimeID)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasGroup(Integer groupID) {

        for (Class c : classes) {
            if (c.getGroupID().equals(groupID)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Modifiers
     */
    public boolean addClass(Class c) {
        classes.add(c);
        return true;
    }

    public boolean removeClass(Class c) {
        return classes.remove(c);
    }

    public Integer getScore() {
        return score;
    }

    public boolean setScore(Integer score) {
        this.score = score;
        return true;
    }

    public TreeSet<Pair<Integer, String>> getAvailableSlots() {
        TreeSet<Pair<Integer, String>> slots = new TreeSet<>(keys);

        for (Class c : classes) {
            slots.remove(new Pair<>(c.getDateTimeID(), c.getClassroomID()));
        }

        return slots;
    }

    @Override
    public int compareTo(Object o) {
        /* To-Do: Implement this to compare schedules and be able to select the very best */
        return 0;
    }

}
