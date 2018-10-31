package Domain;

import java.util.ArrayList;

public class Schedule {

    public ArrayList<Class> getClasses;
    /**
     * Attributes
     */

    Restrictions restrictions;
    ArrayList<Class> classes;

    /**
     * Constructors / Destructors
     */

    public Schedule() {
        restrictions = null;
        classes = null;
    }

    public Schedule(Restrictions restrictions, ArrayList<Class> classes)
    {
        this.restrictions = restrictions;
        this.classes = classes;
    }


    public ArrayList<Class> getClasses() {
        return classes;
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
