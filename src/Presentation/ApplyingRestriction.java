package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ApplyingRestriction implements Initializable {

    @FXML private HBox applyingBox;
    @FXML private Label restrictionInfo;
    private PairNumberRestriction p;




    public void initData(PairNumberRestriction pn){
        p = pn;
        restrictionInfo.setText(p.getName());

        int restrID = p.getI();
        String restriction = p.getName();
        List<String> rest = new ArrayList<>(Arrays.asList(restriction.split(" ")));

        for(String s : rest) {
            if (s.charAt(0) == '[') {
                ComboBox<String> cb = new ComboBox<>();
                TextField numberField = new TextField();
                numberField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));

                if (s.equals("[Hour]") || s.equals("[StartHour]") || s.equals("[EndHour]")) {
                    for (int h = 0; h < 24; ++h) {
                        cb.getItems().add(Integer.toString(h));
                    }
                    cb.setPromptText(s.substring(1, s.length() - 1));
                    applyingBox.getChildren().add(cb);

                }

                else if (s.equals("[WeekDay]")) {
                    cb = new ComboBox<>(FXCollections.observableArrayList(
                            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
                    applyingBox.getChildren().add(cb);
                }

                else if(s.equals("[Subject]")){
                    ArrayList<String> subjects = PresentationCtrl.getInstance().getSubjects();
                    ObservableList<String> subj = FXCollections.observableArrayList(subjects);
                    cb = new ComboBox<>(subj);
                    cb.setPromptText(s.substring(1, s.length() - 1));
                    applyingBox.getChildren().add(cb);
                }

                else if(s.equals("[Int]")){
                    numberField.setPromptText("Int");
                    applyingBox.getChildren().add(numberField);
                }

                else if(s.equals("[Type]")){
                    ArrayList<String> types = PresentationCtrl.getInstance().getGroupTypes();
                    ObservableList<String> t = FXCollections.observableArrayList(types);
                    cb = new ComboBox<>(t);
                    cb.setPromptText(s.substring(1, s.length() - 1));
                    applyingBox.getChildren().add(cb);
                }

                else if(s.equals("[Extra]")){
                    TreeSet<String> e = PresentationCtrl.getInstance().getAllExtras();
                    ArrayList<String> ex = new ArrayList<>();
                    for(String extra : e){
                        ex.add(extra);
                    }
                    ObservableList<String> t = FXCollections.observableArrayList(ex);
                    cb = new ComboBox<>(t);
                    cb.setPromptText(s.substring(1, s.length() - 1));
                    applyingBox.getChildren().add(cb);
                }

                else if(s.equals("[Classroom]")){
                    ArrayList<ArrayList<String>> classrooms = PresentationCtrl.getInstance().getClassroomInfo();
                    ArrayList<String> c = new ArrayList<>();
                    for(ArrayList<String> cl : classrooms){
                        c.add(cl.get(0));
                    }
                    ObservableList<String> t = FXCollections.observableArrayList(c);
                    cb = new ComboBox<>(t);
                    cb.setPromptText(s.substring(1, s.length() - 1));
                    applyingBox.getChildren().add(cb);
                }
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void cancelPressed(ActionEvent event) throws IOException {
        Parent ViewParent = FXMLLoader.load(getClass().getResource("RestrictionView.fxml"));
        Scene ViewScene = new Scene(ViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ViewScene);
        window.show();
    }
}
