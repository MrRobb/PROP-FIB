package Domain;

public class Schedule {

    /**
     * Attributes
     */

    Restrictions restrictions;

    /**
     * Constructors / Destructors
     */

    public Schedule() {
        restrictions = null;
    }

    public Schedule(Restrictions restrictions) {
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
