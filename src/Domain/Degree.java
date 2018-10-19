package Domain;

import java.io.File;
import java.util.ArrayList;

public class Degree {

     enum Curriculum{
        Quarterly,
        Half_yearly
    }

    /**
     * Attributes
     */

    private String name;
    private Integer credits;
    private Curriculum type;
    private ArrayList<String> typeOfGroups;

    /**
     * Constructor / Destructor
     */

    public Degree() {
        this.name = null;
        this.credits = null;
        this.type = null;
        this.typeOfGroups = null;
    }

    public Degree(String name, Integer credits, Curriculum type, ArrayList<String> typeOfGroups) {
        this.name = name;
        this.credits = credits;
        this.type = type;
        this.typeOfGroups = typeOfGroups;
    }

    public Degree (File f) {

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

    public Curriculum getType() {
        return type;
    }

    public boolean setType(Curriculum type) {
        this.type = type;
        return true;
    }

    public ArrayList<String> getTypeOfGroups() {
        return typeOfGroups;
    }

    public boolean setTypeOfGroups(ArrayList<String> typeOfGroups) {
        this.typeOfGroups = typeOfGroups;
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

    public boolean addTypeOfGroups(String tg) {
        if (this.hasTypeOfGroup(tg)) return false;
        typeOfGroups.add(tg);
        return true;
    }

}
