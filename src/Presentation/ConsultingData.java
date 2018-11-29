package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConsultingData implements Initializable {
    @FXML FlowPane subjectPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subjectPane.setHgap(5);
        subjectPane.setVgap(5);
        ArrayList<String> subjects = PresentationCtrl.getInstance().getSubjects();
        for(String s : subjects){
            subjectPane.getChildren().add(new Label(s));
        }
    }
}
