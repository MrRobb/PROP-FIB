package Presentation;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class PairNumberRestriction {
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
    public String getName(){return name;}

    public CheckBox getMandatory() {
        return mandatory;
    }

    public void setMandatory(CheckBox mandatory) {
        this.mandatory = mandatory;
    }
}
