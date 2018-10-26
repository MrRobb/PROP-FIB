package Domain;

import java.io.File;
import java.util.ArrayList;

public class Degree {

    /**
     * Attributes
     */

    private String name;
    private Integer credits;
    private ArrayList<String> typeOfGroups;
    private ArrayList<Subject> subjects;


    /**
     * Constructor / Destructor
     */

    public Degree() {
        this.name = null;
        this.credits = null;
        this.typeOfGroups = null;
    }

    public Degree(String name, Integer credits, Curriculum type, ArrayList<String> typeOfGroups, ArrayList<Subject> subjects) {
        this.name = name;
        this.credits = credits;
        this.typeOfGroups = typeOfGroups;
        this.subjects = subjects;
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
    

    public ArrayList<String> getTypeOfGroups() {
        return typeOfGroups;
    }

    public boolean setTypeOfGroups(ArrayList<String> typeOfGroups) {
        this.typeOfGroups = typeOfGroups;
        return true;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public boolean setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
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
