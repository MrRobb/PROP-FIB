package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Restrictions {

    private HashMap<String, Restriction> restrictions;
    private HashMap<String, Restriction> available;
    private static Restrictions instance = null;

    /*
        Constructor / Destructor
     */
    private Restrictions() {

    }

    public static Restrictions getInstance() {
        if (instance == null) {
            instance = new Restrictions();
        }

        return instance;
    }

    /*
        Modifiers
     */
    public boolean add(String name, ArrayList<Object[]> args) {

        if (exists(name)) {
            return false;
        }

        if (!isAvailable(name)) {
            return false;
        }

        Restriction restriction = getAvailableRestriction(name);

        restriction.setParameters(args);

        restrictions.put(name, restriction);
        return true;
    }

    public boolean addAvailable(String name, Restriction restriction) {

        if (isAvailable(name)) {
            return false;
        }

        available.put(name, restriction);

        return true;
    }

    public boolean delete(String name) {

        if (!exists(name)) {
            return false;
        }

        restrictions.remove(name);
        return true;
    }

    public boolean deleteAvailable(String name) {

        if (!isAvailable(name)) {
            return false;
        }

        restrictions.remove(name);
        available.remove(name);

        return true;
    }

    /*
        Consultors
     */

    public boolean Check(Schedule schedule) {

        Integer score = 0;

        In commonInput = new In(schedule, null);

        for(Restriction restriction : restrictions.values()) {
            if (restriction.apply(commonInput)) {
                score += restriction.getScore();
            }
            else if (restriction.isMandatory()) {
                schedule.setScore(Integer.MIN_VALUE);
                return false;
            }
            else {
                score -= restriction.getScore();
            }
        }

        schedule.setScore(score);

        return true;
    }

    public Set<String> getRestrictionNames() {
        return restrictions.keySet();
    }

    public Set<String> getAvailableRestriccionsNames() {
        return available.keySet();
    }

    public boolean isAvailable(String name) {
        return available.containsKey(name);
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

    public Restriction getAvailableRestriction(String name) {
        return available.get(name);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
