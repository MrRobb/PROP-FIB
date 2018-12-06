package Domain;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Schedule implements Comparable<Schedule> {

    /**
     * Attributes
     */

    private TreeSet<Class> classes;
    private int score = 0;
    private TreeSet<ScheduleKey> keys;

    /**
     * Constructors / Destructors
     */

    public Schedule(Set<Integer> dates, Set<String> classrooms) {

        classes = new TreeSet<>();

        keys = new TreeSet<>();
        for (String classroom : classrooms) {
            for (Integer date : dates) {
                keys.add(new ScheduleKey(date, classroom));
            }
        }
    }

    public Schedule(Schedule schedule) {
        this.classes = new TreeSet<>(schedule.classes);
        this.score = schedule.getScore();
        this.keys = new TreeSet<>(schedule.keys);
    }

    /**
     * Consultants
     */
    public TreeSet<Class> getClasses() {
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
        return classes.add(c);
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

    public JSONObject toJSONObject(){
        JSONArray sch = new JSONArray();
        for(Class c : classes){
            JSONObject cl = new JSONObject();
            cl.put("day", c.getDateTime().getWeekday().toString());
            cl.put("startHour", c.getDateTime().getStartHour());
            cl.put("endHour", c.getDateTime().getEndHour(c.getGroup().getDuration()));
            cl.put("classroom", c.getClassroom().getName());
            cl.put("subject", c.getGroup().getSubject().getName());
            cl.put("group", c.getGroup().getName());
            sch.add(cl);
        }
        JSONObject o = new JSONObject();
        o.put("classes",sch);
        return o;
    }


}
