package Domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

class Schedule implements Comparable<Schedule> {

    /**
     * Attributes
     */

    private TreeSet<Class> classes;
    private int score = 0;
    static private TreeSet<ScheduleKey> keys;

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
        this.classes = (TreeSet<Class>) schedule.classes.clone();
        this.score = schedule.getScore();
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

    public TreeSet<ScheduleKey> getAvailableSlots(int duracion) {
        TreeSet<ScheduleKey> slots = new TreeSet<>(keys);

        for (Class c : classes) {
            DateTime date = c.getDateTime();
            Group group = c.getGroup();

            for (int i = 0; i < group.getDuration(); i++) {
                slots.remove(new ScheduleKey(DateTimes.getInstance().getID(date), c.getClassroomID()));
                date = DateTimes.getInstance().next(date);
            }
        }

        DateTime last = DateTimes.getInstance().lastPossible();
        for (int i = 0; i < duracion; i++) {

            slots.removeIf((ScheduleKey slot) -> DateTimes.getInstance().get(slot.getKey()).equals(last));

            last.setDateTime(DateTimes.getInstance().prev(last));
        }

        return slots;
    }

    @Override
    public int compareTo(Schedule other) {
        if (this.score != other.score) {
            return other.score - this.score; // - antes
        }
        else {
            Iterator it1 = this.classes.iterator();
            Iterator it2 = other.classes.iterator();

            while (it1.hasNext() && it2.hasNext()) {
                Class c1 = (Class)it1.next();
                Class c2 = (Class)it2.next();
                int comp = c1.compareTo(c2);
                if (comp != 0) {
                    return comp;
                }
            }

            if (it1.hasNext()) {
                int i = -1;
                while (it1.hasNext()) {
                    it1.next();
                    i--;
                }
                return i;
            }
            if (it2.hasNext()) {
                int i = 1;
                while (it2.hasNext()) {
                    it2.next();
                    i++;
                }
                return i;
            }

            return 0;
        }
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
            cl.put("datetimeID", c.getDateTimeID());
            cl.put("classroomID", c.getClassroomID());
            cl.put("groupID", c.getGroupID());
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
