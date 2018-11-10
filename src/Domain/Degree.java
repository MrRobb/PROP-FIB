package Domain;

import java.util.ArrayList;

public class Degree {

    /**
     * Attributes
     */

    private String name;
    private Integer credits;
    private ArrayList<String> typeOfGroups;
    private static Degree instance = null;


    /**
     * Constructor / Destructor
     */

    private Degree () {
        name = null;
        credits = null;
        typeOfGroups = null;
    }

    public static Degree getInstance(){
        if (instance == null) {
            instance = new Degree();
        }
        return instance;
    }


    /**
     * Getters / Setters
     */

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public Integer getCredits() {
        return credits;
    }

    public boolean setCredits(Integer credits) {
        this.credits = credits;
        return true;
    }
    

    public ArrayList<String> getTypeOfGroups() {
        return typeOfGroups;
    }

    public boolean setTypeOfGroups(ArrayList<String> typeOfGroups) {
        this.typeOfGroups = typeOfGroups;
        return true;
    }

    public boolean addTypeOfGroups(String tg) {
        if (this.hasTypeOfGroup(tg)) return false;
        typeOfGroups.add(tg);
        return true;
    }


    /**
     * Consultants
     */

    public boolean hasTypeOfGroup(String tg) {
        for (String s: this.typeOfGroups) {
            if (s.equals(tg)) return true;
        }
        return false;
    }

    public boolean clear() {
        Degree.getInstance().name = null;
        Degree.getInstance().credits = null;
        Degree.getInstance().typeOfGroups.clear();
        return true;
    }

}
