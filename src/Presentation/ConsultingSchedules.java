package Presentation;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Thread.yield;

public class ConsultingSchedules implements Initializable {

    @FXML private ProgressBar progressBar;
    @FXML private Label progressLabel;
    @FXML private GridPane schedulePane;
    @FXML private ComboBox classroomComboBox;

    @FXML private Label scheduleNumber;
    @FXML private Button previousSchedule;
    @FXML private Button nextSchedule;
    @FXML private Button backToMenu;
    @FXML private Button saveSchedule;
    @FXML private Button deleteSchedule;

    private boolean isExploring = false;
    private int iSchedule = -1;
    private Tile selectedNode = null;
    private boolean user = false;

    class Tile extends Button {

        public Tile() {
            super();

            getStylesheets().add(getClass().getResource("main.css").toExternalForm());
            setAlignment(Pos.CENTER);
            addClass(null);

            setOnMouseClicked(e -> {

                // Is a current class (set as selected node)
                if (getText() != null) {
                    selectedNode = this;
                }

                // Is empty (move selected node to empty)
                else {
                    if (selectedNode != null) {
                        this.addClass(selectedNode.getText());
                        selectedNode.addClass(null);
                        selectedNode = null;
                    }
                }
            });
        }

        public void addClass(String classname) {

            setText(classname);

            if (classname != null) {
                getStyleClass().clear();
                getStyleClass().add("classTile");
            }
            else {
                getStyleClass().clear();
                getStyleClass().add("transparentTile");
            }

            GridPane.setFillHeight(this, true);
            GridPane.setFillWidth(this, true);
            setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Set active view
        PresentationCtrl.getInstance().setWindow(this);

        // Show default text
        progressLabel.setText("");
        progressBar.setVisible(false);
        scheduleNumber.setText("No schedule generated yet");

        javafx.scene.image.Image image = new Image(getClass().getResourceAsStream("back.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        backToMenu.setGraphic(imageView);

        // Load classrooms
        ArrayList<String> classrooms = PresentationCtrl.getInstance().getUsedClassroomNames(0);
        ObservableList<String> classList = FXCollections.observableArrayList(classrooms);
        classroomComboBox.setItems(classList);

        // Select classroom
        if (classList.size() > 0) {
            classroomComboBox.setValue(classList.get(0));
        }

        // Show
        iSchedule = 0;
        ShowSchedule(iSchedule);

    }


    private int getDayIndex(String day) {
        if (day.equalsIgnoreCase("Monday")) {
            return 1;
        }
        else if (day.equalsIgnoreCase("Tuesday")) {
            return 2;
        }
        else if (day.equalsIgnoreCase("Wednesday")) {
            return 3;
        }
        else if (day.equalsIgnoreCase("Thursday")) {
            return 4;
        }
        else if (day.equalsIgnoreCase("Friday")) {
            return 5;
        }
        else {
            return 0;
        }
    }

    private String getDayName(int index) {
        switch (index) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            default:
                return "Date";
        }
    }

    private boolean ShowSchedule(int index) {

        // Error checking
        if (index < 0 || PresentationCtrl.getInstance().getNumberOfSchedules() <= index) {
            return false;
        }

        iSchedule = index;
        schedulePane.getChildren().retainAll(schedulePane.getChildren().get(0));

        scheduleNumber.setText((iSchedule + 1) + " of " + PresentationCtrl.getInstance().getNumberOfSchedules());

        // Create columns
        for (int j = 0; j < 6; j++) {
            Label dayName = new Label(getDayName(j));
            dayName.setAlignment(Pos.CENTER);
            schedulePane.add(dayName, j, 0);
            GridPane.setFillHeight(dayName, true);
            GridPane.setFillWidth(dayName, true);
        }

        // Create rows
        for (int i = 1; i < 25; i++) {
            Label hourName = new Label(Integer.toString(i - 1));
            hourName.setAlignment(Pos.CENTER);
            schedulePane.add(hourName, 0, i);
            GridPane.setFillHeight(hourName, true);
            GridPane.setFillWidth(hourName, true);
        }

        // Get classes of schedule
        JSONArray schedulesJSON = PresentationCtrl.getInstance().getSavedSchedules();
        JSONObject schedule = (JSONObject) schedulesJSON.get(index);
        JSONArray classesJSON = (JSONArray) schedule.get("classes");

        // Default tiles
        for (int i = 1; i < 25; i++) {
            for (int j = 1; j < 6; j++) {
                schedulePane.add(new Tile(), j, i);
            }
        }

        // Add classes
        for (Object classObj : classesJSON)
        {
            JSONObject classJSON = (JSONObject) classObj;

            String subject = (String) classJSON.get("subject");
            String group = (String) classJSON.get("group");
            String day = (String) classJSON.get("day");
            String classroom = (String) classJSON.get("classroom");
            int hour = (int) classJSON.get("startHour");

            int dayIndex = getDayIndex(day);
            int hourIndex = hour + 1;

            if (Objects.equals(classroom, classroomComboBox.getValue())) {
                Tile tile = getTile(schedulePane, dayIndex, hourIndex);
                if (tile != null) {
                    tile.addClass(subject + " " + group);
                }
            }
        }

        schedulePane.setHgap(10);
        schedulePane.setVgap(10);
        GridPane.setHalignment(schedulePane, HPos.CENTER);
        GridPane.setValignment(schedulePane, VPos.CENTER);

        return true;
    }

    private Tile getTile(GridPane schedulePane, int dayIndex, int hourIndex) {

        for (Node children : schedulePane.getChildren().filtered(
                (node) -> node.getClass() == Tile.class)
        ) {
            Integer col = GridPane.getColumnIndex(children);
            Integer row = GridPane.getRowIndex(children);
            if (col == dayIndex && row == hourIndex) {
                return (Tile) children;
            }
        }

        return null;
    }

    public void changeClassroom(ActionEvent event) {
        ShowSchedule(iSchedule);
    }


    public void previousSchedule(ActionEvent event) {
        if (0 <= iSchedule - 1 && iSchedule - 1 < PresentationCtrl.getInstance().getNumberOfSchedules()) {
            iSchedule--;
            classroomComboBox.getSelectionModel().clearSelection();
            ShowSchedule(iSchedule);
        }
    }

    public void nextSchedule(ActionEvent event) {
        if (0 <= iSchedule + 1 && iSchedule + 1 < PresentationCtrl.getInstance().getNumberOfSchedules()) {
            iSchedule++;
            classroomComboBox.getSelectionModel().clearSelection();
            ShowSchedule(iSchedule);
        }
    }

    public void saveSchedule(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save schedule");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON (with schedules)", "*.json"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            PresentationCtrl.getInstance().saveSchedule(iSchedule, file.getPath());
        }
    }

    public void backToMenu(ActionEvent event) {
        Parent ViewParent = null;
        try {
            ViewParent = FXMLLoader.load(getClass().getResource(user ? "ActionUser.fxml" : "Action.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene ViewScene = new Scene(ViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ViewScene);
        window.show();
    }

    public void deleteSchedule(ActionEvent event){
        Boolean b = PresentationCtrl.getInstance().deleteSchedule(iSchedule);
        PresentationCtrl.getInstance().updateNumberOfSchedules();

        // Load classrooms
        ArrayList<String> classrooms = PresentationCtrl.getInstance().getUsedClassroomNames(0);
        ObservableList<String> classList = FXCollections.observableArrayList(classrooms);
        classroomComboBox.setItems(classList);

        // Select classroom
        if (classList.size() > 0) {
            classroomComboBox.setValue(classList.get(0));
        }

        // Show
        iSchedule--;
        if(iSchedule == -1) iSchedule = 0;
        ShowSchedule(iSchedule);

    }

    public void initUser(int u){
        if(u == 1) {
            user = true;
            deleteSchedule.setVisible(false);

        }
    }
}