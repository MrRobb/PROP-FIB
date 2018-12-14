package Presentation;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class PairNumberRestriction implements Comparable<PairNumberRestriction> {
    private Integer i = 0;
    private String name = "";
    private CheckBox mandatory;

    public PairNumberRestriction(Integer i, String name){
        this.i = i;
        this.name = name;
    }

    public PairNumberRestriction(Integer i, String name, CheckBox mand){
        this.i = i;
        this.name = name;
        this.mandatory = mand;
    }

    public Integer getI(){return i; }
    public Boolean setI(int i){
        this.i = i;
        return true;
    }

    public String getName(){return name;}

    public CheckBox getMandatory() {
        return mandatory;
    }

    public void setEditable(CheckBox mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public int compareTo(PairNumberRestriction r){
        if(this.mandatory.isDisable() && !r.mandatory.isDisable()) return -1;
        else if(!this.mandatory.isDisable() && r.mandatory.isDisable()) return 1;
        else{
            return this.name.compareTo(r.name);
        }
    }
}
