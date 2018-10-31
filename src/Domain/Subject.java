package Domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alberto Gimenez Aragon
 */


public class Subject {

    /**
     * Attributes
     */

    private String name;
    private Integer semester;
    private Double credits;
    private Boolean mandatory;
    private String speciality;


    /**
     * Constructor / Destructor
     */

    Subject(String name, Integer semester, Double credits, Boolean mandatory, String speciality){
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.mandatory = mandatory;
        this.speciality = speciality;

    }

    Subject(String name){
        this.name = name;
        this.semester = 0;
        this.credits = 6.0;
        this.mandatory = true;
        this.speciality = null;

    }

    /**
     * Getters / Setters
     */


    public String getName() { return name; }

    public Integer getSemester() {
        return semester;
    }

    public Double getCredits() {
        return credits;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setCredits(Double credits) { this.credits = credits; }

    public void setMandatory(Boolean mandatory) { this.mandatory = mandatory; }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}
