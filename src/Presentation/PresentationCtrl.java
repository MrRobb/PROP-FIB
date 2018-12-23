package Presentation;

import Domain.DomainCtrl;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.TreeSet;

public class PresentationCtrl {
    private static PresentationCtrl instance = null;
    private Object window = null;
    private final ReadOnlyDoubleWrapper progress = new ReadOnlyDoubleWrapper();
    private final ReadOnlyIntegerWrapper nSchedules = new ReadOnlyIntegerWrapper();

    private PresentationCtrl() {
    }

    public synchronized static PresentationCtrl getInstance() {
        if (instance == null) {
            instance = new PresentationCtrl();
            DomainCtrl.getInstance();
        }
        return instance;
    }

    public boolean setWindow(Object window) {
        this.window = window;
        return true;
    }

    public ArrayList<String> getSubjects() {
        return DomainCtrl.getInstance().getSubjects();
    }

    public Boolean produce(String file) {
        return DomainCtrl.getInstance().produceFactory(file);
    }

    public Integer getNumberOfSubjects() {
        return DomainCtrl.getInstance().getNumberOfSubjects();
    }

    public Integer getNumberOfGroups() {
        return DomainCtrl.getInstance().getNumberOfGroups();
    }

    public Integer getNumberOfClassrooms(){
        return DomainCtrl.getInstance().getNumberOfClassrooms();
    }

    public Integer getNumberOfSchedules() {
        return nSchedules.getValue();
    }

    public boolean saveSchedule(int iSchedule, String filepath) {
        if (0 <= iSchedule && iSchedule <= DomainCtrl.getInstance().getNumberOfSchedules()) {
            return DomainCtrl.getInstance().saveSchedule(iSchedule, filepath);
        }
        return false;
    }

    public JSONArray getSavedSchedules() {
        return DomainCtrl.getInstance().getSavedSchedules();
    }

    public ArrayList<String> getUsedClassroomNames(int iSchedule) {
        return DomainCtrl.getInstance().getUsedClassroomNames(iSchedule);
    }

    public ArrayList<String> getClassroomNames() {
        return DomainCtrl.getInstance().getClassroomNames();
    }

    public ArrayList<ArrayList<String>> getClassroomInfo() {
        return DomainCtrl.getInstance().getClassroomInfo();
    }

    public ArrayList<ArrayList<String>> getGroupsInfo() {
        return DomainCtrl.getInstance().getGroupInfo();
    }

    public ArrayList<String> getAvailableRestrictions() {
        return DomainCtrl.getInstance().getAvailableRestrictions();
    }

    public ArrayList<String> getAppliedRestrictions() {
        return DomainCtrl.getInstance().getAppliedRestrictions();
    }

    public ArrayList<String> getGroupTypes() {
        return DomainCtrl.getInstance().getGroupTypes();
    }

    public TreeSet<String> getAllExtras() {
        return DomainCtrl.getInstance().getAllExtras();
    }

    public Boolean applyRestriction(String id, ArrayList<String> args) {
        return DomainCtrl.getInstance().applyRestriction(id,args);
    }

    public Boolean deleteAppliedRestriction(String id) {
        return DomainCtrl.getInstance().deleteAppliedRestriction(id);
    }

    public void generate() {
        DomainCtrl.getInstance().generateSchedule();
    }

    public boolean startGenerating() {
        try {
            GeneratingSchedules generatingWindow = (GeneratingSchedules)window;
            return generatingWindow.startExploring();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean endGenerating() {
        try {
            GeneratingSchedules generatingWindow = (GeneratingSchedules)window;
            return generatingWindow.endExploring();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean setProgress(double progress) {
        try {
            this.progress.set(progress);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    public synchronized boolean setProgress(double progress) {
        try {
            GeneratingSchedules generatingWindow = (GeneratingSchedules)window;
            return generatingWindow.setProgress(progress);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    */

    public boolean updateNumberOfSchedules() {
        this.nSchedules.set(DomainCtrl.getInstance().getNumberOfSchedules());
        return true;
    }

    public Boolean isRestrictionEditable(String s) {
        return DomainCtrl.getInstance().isRestrictionEditable(s);
    }

    public Boolean updateScore(String res, int score){
        return DomainCtrl.getInstance().updateScore(res,score);
    }

    public void setAppliedRestriction(Boolean b, String res) { DomainCtrl.getInstance().setAppliedRestriction(b,res); }

    public Boolean getAppliedRestriction(String res){ return DomainCtrl.getInstance().getAppliedRestriction(res); }
    
    public Boolean deleteSchedules() {
        return DomainCtrl.getInstance().clearSavedSchedules();
    }

    public Boolean importSchedules(String path) {
        return DomainCtrl.getInstance().importSchedules(path);
    }

    public int getYesNo(String title, String header, String content) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOne){
            return 1;
        } else {
            return 2;
        }
    }

    public double getProgress() {
        return progressProperty().get();
    }

    public ReadOnlyDoubleProperty progressProperty() {
        return progress;
    }

    public Boolean setMaxSchedules(int n){ return DomainCtrl.getInstance().setMaxSchedules(n); }

    public Boolean saveAppliedRestrictions(String filepath) {
        return DomainCtrl.getInstance().saveAppliedRestrictions(filepath);
    }

    public Boolean importRestrictions(String filepath) {
        return DomainCtrl.getInstance().importRestrictions(filepath);
    }

    public Boolean deleteSchedule(int i){ return DomainCtrl.getInstance().deleteSchedule(i); }

    public boolean moveClass(JSONObject oldC, JSONObject newC, int iSchedule) {
        return DomainCtrl.getInstance().moveClass(oldC, newC, iSchedule);
    }

    public int getDateTimeID(int hour, String day) {
        return DomainCtrl.getInstance().getDateTimeID(hour, day);
    }

    public boolean getScore(int iSchedule) {
        return DomainCtrl.getInstance().getScore(iSchedule);
    }

    public void setMandatory(String id, boolean isMandatory) {
        DomainCtrl.getInstance().setMandatory(id, isMandatory);
    }
}
