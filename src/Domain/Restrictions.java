package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Restrictions {

    private HashMap<String, Restriction> applieds;
    private HashMap<String, Restriction> available;
    private static Restrictions instance = null;

    /*
        Constructor / Destructor
     */
    private Restrictions() {
        applieds = new HashMap<>();
        available = new HashMap<>();
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
    public boolean addApplied(String name, ArrayList<Object[]> args) {

        if (exists(name)) {
            return false;
        }

        if (!isAvailable(name)) {
            return false;
        }

        Restriction restriction = getAvailableRestriction(name);

        restriction.setParameters(args);

        applieds.put(name, restriction);
        return true;
    }

    public boolean clearApplied() {
        applieds.clear();
        return true;
    }

    public boolean addAvailable(String name, Restriction restriction) {

        if (isAvailable(name)) {
            return false;
        }

        available.put(name, restriction);

        return true;
    }

    public boolean deleteApplyed(String name) {

        if (!exists(name)) {
            return false;
        }

        applieds.remove(name);
        return true;
    }

    public boolean deleteAvailable(String name) {

        if (!isAvailable(name)) {
            return false;
        }

        applieds.remove(name);
        available.remove(name);

        return true;
    }

    /*
        Consultors
     */

    public boolean Check(Schedule schedule) {

        Integer score = 0;

        In commonInput = new In(schedule, null);

        for(Restriction restriction : applieds.values()) {
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

    public Set<String> getAppliedRestrictionNames() {
        return applieds.keySet();
    }

    public Set<String> getAvailableRestriccionsNames() {
        return available.keySet();
    }

    public boolean isAvailable(String name) {
        return available.containsKey(name);
    }

    public boolean exists(String name) {
        return applieds.containsKey(name);
    }

    public boolean exists(Restriction restriction) {
        return applieds.containsValue(restriction);
    }

    public Restriction getAppliedRestriccion(String name) {
        return applieds.get(name);
    }

    public Restriction getAvailableRestriction(String name) {
        return available.get(name);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
