package Domain;

import java.util.ArrayList;

public class Schedule implements Comparable {

    public ArrayList<Class> getClasses;
    /**
     * Attributes
     */

    ArrayList<Class> classes;
    Integer score = null;

    /**
     * Constructors / Destructors
     */

    public Schedule() {
        classes = null;
    }

    public Schedule(Restrictions restrictions, ArrayList<Class> classes)
    {
        this.classes = classes;
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

    @Override
    public int compareTo(Object o) {
        /* To-Do: Implement this to compare schedules and be able to select the very best */
        return 0;
    }

    public Integer getScore() {
        return score;
    }

    public boolean setScore(Integer score) {
        this.score = score;
        return true;
    }
}
