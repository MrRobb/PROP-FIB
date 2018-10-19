package Domain;

public class Timetable {

    /**
     * Attributes
     */

    Restrictions restrictions;

    /**
     * Constructors / Destructors
     */

    public Timetable () {
        restrictions = null;
    }

    public Timetable (Restrictions restrictions) {
        this.restrictions = restrictions;
    }


    /**
     * Getters / Setters
     */

    public Restrictions getRestrictions() {
        return restrictions;
    }

    public boolean setRestrictions(Restrictions restrictions) {
        this.restrictions = restrictions;
        return true;
    }

    /**
     * Consultants
     */

}
