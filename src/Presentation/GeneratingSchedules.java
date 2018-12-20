package Presentation;

import Domain.DomainCtrl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GeneratingSchedules implements Initializable {

    @FXML private ProgressBar progressBar;
    @FXML private Label progressLabel;
    @FXML private Label secondsLabel;

    @FXML private GridPane schedulePane;
    @FXML private AnchorPane anchorPane;
    @FXML private ScrollPane scrollPane;

    @FXML private ComboBox classroomComboBox;

    private boolean isExploring = false;
    private int iSchedule = -1;

    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {

        // Set active view
        PresentationCtrl.getInstance().setWindow(this);

        // Show default text
        secondsLabel.setText("");
        progressLabel.setText("");
        progressBar.setVisible(false);

        // Launch generate
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        //Background work
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try{
                                PresentationCtrl.getInstance().generate();
                            } finally {
                                latch.countDown();
                            }
                        });
                        latch.await();
                        //Keep with the background work
                        return null;
                    }
                };
            }
        };
        service.start();
    }

    public synchronized boolean setProgress(int progress) {
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
        ShowSchedule(0);

        return true;
    }

    private Pair<Integer, Integer> GetScheduleSize(JSONObject schedule) {

        // Get classes of schedule
        JSONArray classesJSON = (JSONArray) schedule.get("classes");
        int min = 23;
        int max = 0;

        // List classes
        for (Object classObj : classesJSON)
        {
            JSONObject classJSON = (JSONObject) classObj;
            int startHour = (int)(long)classJSON.get("startHour");
            int endHour = (int)(long)classJSON.get("endHour");
            if (endHour < startHour) {
                max = 23;
                min = min(min, endHour);
            }
            else {
                min = min(min, startHour);
                max = max(max, endHour);
            }
        }

        return new Pair<>(min, max);
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
        }

        // Create rows
        for (int i = 1; i < 25; i++) {
            Label hourName = new Label(Integer.toString(i - 1));
            hourName.setAlignment(Pos.CENTER);
            schedulePane.add(hourName, 0, i);
        }

        // Get classes of schedule
        JSONArray schedulesJSON = PresentationCtrl.getInstance().getSavedSchedules();
        JSONObject schedule = (JSONObject) schedulesJSON.get(index);
        JSONArray classesJSON = (JSONArray) schedule.get("classes");

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

            System.out.println(classroom);
            if (classroomComboBox.getValue() != null) {
                System.out.println("Classroom: " + (String)classroomComboBox.getValue());
            }
            else {
                System.out.println("null");
            }

            if (Objects.equals(classroom, (String) classroomComboBox.getValue())) {
                schedulePane.add(new Button(subject + " " + group), dayIndex, hourIndex);
            }
        }

        schedulePane.setHgap(10);
        schedulePane.setVgap(10);
        GridPane.setHalignment(schedulePane, HPos.CENTER);
        GridPane.setValignment(schedulePane, VPos.CENTER);

        return true;
    }

    public void changeClassroom(ActionEvent event) {
        ShowSchedule(iSchedule);
    }
}
