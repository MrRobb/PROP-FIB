package Domain;

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

    public Degree () {
        name = null;
        credits = null;
        typeOfGroups = null;
        subjects = null;
    }

    public Degree(String name, Integer credits, ArrayList<String> typeOfGroups, ArrayList<Subject> subjects) {
        this.name = name;
        this.credits = credits;
        this.typeOfGroups = typeOfGroups;
        this.subjects = subjects;
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

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public boolean setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
        return true;
    }

    public boolean addSubject (Subject subject) {
        for (Subject s: this.subjects) {
            if (s.getClass().equals(subject.getClass())) return true;
        }
        subjects.add(subject);
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

    public boolean hasSubject(Subject subject) {
        for (Subject s: this.subjects) {
            if (s.getClass().equals(subject.getClass())) return true;
        }
        return false;
    }

}
