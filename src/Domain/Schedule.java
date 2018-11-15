package Domain;

import javafx.util.Pair;
import java.util.*;

public class Schedule implements Comparable<Schedule> {

    /**
     * Attributes
     */

    private LinkedHashSet<Class> classes;
    private int score = 0;
    private TreeSet<ScheduleKey> keys = null;

    /**
     * Constructors / Destructors
     */

    public Schedule(Set<Integer> dates, Set<String> classrooms) {

        classes = new LinkedHashSet<>(Groups.getInstance().size());

        keys = new TreeSet<>();
        for (String classroom : classrooms) {
            for (Integer date : dates) {
                keys.add(new ScheduleKey(date, classroom));
            }
        }
    }

    public Schedule(Schedule schedule) {
        this.classes = new LinkedHashSet<>(schedule.classes);
        this.score = schedule.getScore();
        this.keys = new TreeSet<>(schedule.keys);
    }

    /**
     * Consultants
     */
    public LinkedHashSet<Class> getClasses() {
        return classes;
    }

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

    public int getScore() {
        return score;
    }

    public boolean setScore(int score) {
        this.score = score;
        return true;
    }

    public TreeSet<ScheduleKey> getAvailableSlots() {
        TreeSet<ScheduleKey> slots = new TreeSet<>(keys);

        for (Class c : classes) {
            DateTime date = c.getDateTime();
            Group group = c.getGroup();

            for (int i = 0; i < group.getDuration(); i++) {
                slots.remove(new ScheduleKey(DateTimes.getInstance().getID(date), c.getClassroomID()));
                date = DateTimes.getInstance().next(date);
            }
        }

        return slots;
    }

    @Override
    public int compareTo(Schedule other) {
        return this.getScore() - other.getScore();
    }

    @Override
    public String toString() {

        StringBuilder schedule = new StringBuilder();

        for (Class c : classes) {
            schedule.append(c.toString())
                    .append('\n');
        }

        return schedule.toString();
    }
}
