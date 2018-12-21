package Presentation;

import Domain.DomainCtrl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Thread.yield;

public class GeneratingSchedules implements Initializable {

    @FXML private ProgressBar progressBar;
    @FXML private Label progressLabel;
    @FXML private Label secondsLabel;
    @FXML private GridPane schedulePane;
    @FXML private ComboBox classroomComboBox;

    private boolean isExploring = false;
    private int iSchedule = -1;
    private Tile selectedNode = null;

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
    public synchronized void initialize(URL location, ResourceBundle resources) {

        // Set active view
        PresentationCtrl.getInstance().setWindow(this);

        // Show default text
        secondsLabel.setText("");
        progressLabel.setText("");
        progressBar.setVisible(false);
    }

    public synchronized boolean setProgress(double progress) {
        if (!isExploring) {
            return false;
        }

        progressBar.setProgress(progress);
        return true;
    }

    public synchronized boolean setRemainingSeconds(int seconds) {
        if (!isExploring) {
            return false;
        }

        secondsLabel.setText(seconds + "seconds remaining");
        return true;
    }

    public synchronized boolean startExploring() {
        if (isExploring) {
            return false;
        }

        isExploring = true;
        progressLabel.setText("Exploring schedules...");
        progressBar.setVisible(true);
        return true;
    }

    public synchronized boolean endExploring() {
        if (!isExploring) {
            return false;
        }

        // Finish progress
        isExploring = false;
        progressLabel.setText("Finished exploring");

        // Load classrooms
        ArrayList<String> classrooms = PresentationCtrl.getInstance().getUsedClassroomNames(0);
        ObservableList<String> classList = FXCollections.observableArrayList(classrooms);
        classroomComboBox.setItems(classList);

        // Show
        classroomComboBox.setValue(classList.get(0));
        ShowSchedule(0);

        return true;
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
        if (index < 0 || DomainCtrl.getInstance().getNumberOfSchedules() <= index) {
            return false;
        }

        iSchedule = index;

        schedulePane.getChildren().retainAll(schedulePane.getChildren().get(0));

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

    public void enableGeneration() {
        // Launch generate

        PresentationCtrl.getInstance().startGenerating();
        PresentationCtrl.getInstance().setProgress(-1);

        Service service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                Task task = new Task<Void>() {
                    @Override
                    public Void call() {
                        PresentationCtrl.getInstance().progressProperty().addListener(
                                (obs, oldProgress, newProgress) ->
                                updateProgress(newProgress.doubleValue(), 1)
                        );
                        PresentationCtrl.getInstance().generate();
                        return null;
                    }
                };

                task.setOnSucceeded(event -> {
                    PresentationCtrl.getInstance().endGenerating();
                });

                return task;
            }
        };

        progressBar.progressProperty().bind(service.progressProperty());

        new Thread(service::start).start();
    }
}
