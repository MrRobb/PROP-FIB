package Domain;

import java.util.ArrayList;

public class Classroom {

    /**
     * Attributes
     */

    private String name;
    private Integer capacity;
    private ArrayList<String> extras;

    /**
     * Constructor / Destructor
     */

    Classroom(String name, Integer capacity){
        this.name = name;
        this.capacity = capacity;
        extras = new ArrayList<>(0);
    }

    Classroom(String name){
        this.name = name;
        this.capacity = 0;
        extras = new ArrayList<>(0);
    }


    /**
     * Getters / Setters
     */

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public ArrayList<String> getExtras() { return extras; }


    public Boolean hasExtra(String e){
        for(String ex : extras) {
            if (ex == e) return true;
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean addExtra(String e){
        if(this.hasExtra(e)) return false;
        else{
            extras.add(e);
            return true;
        }
    }
}