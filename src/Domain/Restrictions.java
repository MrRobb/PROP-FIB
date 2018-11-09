package Domain;

import java.util.HashMap;
import java.util.Set;

public class Restrictions {

    private HashMap<String, Restriction> restrictions;
    private static Restrictions instance = null;

    /*
        Constructor / Destructor
     */
    private Restrictions() {}

    public static Restrictions getInstance() {
        if (instance == null) {
            instance = new Restrictions();
        }

        return instance;
    }

    /*
        Modifiers
     */
    public boolean add(String name, Restriction restriction) {
        if (exists(name)) {
            return false;
        }

        restrictions.put(name, restriction);
        return true;
    }

    public boolean delete(String name) {
        if (exists(name)) {
            restrictions.remove(name);
            return true;
        }
        else {
            return false;
        }
    }

    /*
        Consultors
     */

    public boolean Check(Schedule schedule) {

        schedule.setScore(0);

        /* To-Do: Check restriction and assign a score for the schedule */

        return true;
    }

    public Set<String> getNames() {
        return restrictions.keySet();
    }

    public boolean exists(String name) {
        return restrictions.containsKey(name);
    }

    public boolean exists(Restriction restriction) {
        return restrictions.containsValue(restriction);
    }

    public Restriction getRestriccion(String name) {
        return restrictions.get(name);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
