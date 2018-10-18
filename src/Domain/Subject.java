package Domain;

import java.util.Map;
import java.util.HashMap;


public class Subject {

    /**
     * Attributes
     */

    private String name;
    private Integer semester;
    private Integer credits;
    private Boolean mandatory;
    private String speciality;
    private Map<String,Integer> weeklyHours;


    /**
     * Constructor / Destructor
     */

    Subject(String name, Integer semester, Integer credits, Boolean mandatory, String speciality){
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.mandatory = mandatory;
        this.speciality = speciality;
        weeklyHours = new HashMap<String, Integer>();

    }

    /**
     * Getters / Setters
     */


    public String getName() { return name; }

    public Integer getSemester() {
        return semester;
    }

    public Integer getCredits() {
        return credits;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Integer getWeeklyHours(String type) {
        if(weeklyHours.containsKey(type)) return weeklyHours.get(type);
        else return -1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setCredits(Integer credits) { this.credits = credits; }

    public void setMandatory(Boolean mandatory) { this.mandatory = mandatory; }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void addWeeklyHours(String type, Integer hours) {
        weeklyHours.put(type,hours);
    }
}
