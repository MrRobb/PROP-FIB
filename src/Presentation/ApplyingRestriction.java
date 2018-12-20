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
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ApplyingRestriction implements Initializable {

    @FXML private HBox applyingBox;
    @FXML private Label restrictionInfo;
    @FXML private Button addRes;
    private PairNumberRestriction p;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }


    public void initData(PairNumberRestriction pn){
        p = pn;
        restrictionInfo.setText(p.getName());

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
                    cb.setPromptText("WeekDay");
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
                    ex.addAll(e);
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


    public void cancelPressed(ActionEvent event) throws IOException {
        Parent ViewParent = FXMLLoader.load(getClass().getResource("RestrictionView.fxml"));
        Scene ViewScene = new Scene(ViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ViewScene);
        window.show();
    }

    private Boolean allFieldsAreFilled(){
        for(Node node : applyingBox.getChildren()){
            if(node instanceof TextField){
                if(((TextField) node).getText().trim().isEmpty()) return false;
            }
            else if(node instanceof ComboBox){
                if(((ComboBox) node).getSelectionModel().isEmpty()) return false;
            }
        }
        return true;
    }


    public void addPressed(ActionEvent event) throws IOException{
        if(!allFieldsAreFilled()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("All fields must be entered");
            alert.setContentText("Please check that all fields are entered correctly and try again.");
            alert.showAndWait();
        }
        else{
            ArrayList<String> args = new ArrayList<>();
            for(Node node : applyingBox.getChildren()){
                if(node instanceof TextField){
                    args.add(((TextField) node).getText());
                }
                else if(node instanceof ComboBox){
                    args.add(((ComboBox) node).getSelectionModel().getSelectedItem().toString());
                }
            }

            if(PresentationCtrl.getInstance().applyRestriction(p.getName(),args)){
                Parent ViewParent = FXMLLoader.load(getClass().getResource("RestrictionView.fxml"));
                Scene ViewScene = new Scene(ViewParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(ViewScene);
                window.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Restrictions already exists");
                alert.setContentText("Please enter a new restriction.");
                alert.showAndWait();
            }
        }
    }
}
