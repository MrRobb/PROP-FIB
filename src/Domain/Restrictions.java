package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Restrictions {

    private ArrayList<Restriction> restrictions;

    /**
      Constructors
     */

    public Restrictions () {
        this.restrictions = null;
    }

    public Restrictions(ArrayList<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    /**
     Getters / Setters
     */

    public boolean setRestrictions(ArrayList<Restriction> restrictions) {
        this.restrictions = restrictions;
        return true;
    }

    public ArrayList<Restriction> getRestrictions() {
        return restrictions;
    }

    /**
     Modifiers
     */

    public boolean addRestriction(Restriction restriction){
        for (Restriction r: this.restrictions) {
            if (r.getClass().equals(restriction.getClass())) return false;
        }
        restrictions.add(restriction);
        return true;
    }

    /**
     Modifiers
     */

    public boolean hasRestriction(Restriction restriction){
        for (Restriction r: this.restrictions) {
            if (r.getClass().equals(restriction.getClass())) return true;
        }
        return false;
    }

}
