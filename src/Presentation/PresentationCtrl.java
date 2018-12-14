package Presentation;

import Domain.DomainCtrl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.TreeSet;

public class PresentationCtrl {
    private static PresentationCtrl instance = null;

    private PresentationCtrl() {}

    public static PresentationCtrl getInstance() {
        if (instance == null) {
            instance = new PresentationCtrl();
        }
        return instance;
    }

    public ArrayList<String> getSubjects(){ return DomainCtrl.getInstance().getSubjects(); }

    public Boolean produce(String file){
        return DomainCtrl.getInstance().produceFactory(file);
    }

    public Integer getNumberOfSubjects(){
        return DomainCtrl.getInstance().getNumberOfSubjects();
    }

    public Integer getNumberOfGroups(){
        return DomainCtrl.getInstance().getNumberOfGroups();
    }

    public Integer getNumberOfClassrooms(){
        return DomainCtrl.getInstance().getNumberOfClassrooms();
    }

    public ArrayList<ArrayList<String>> getClassroomInfo(){ return DomainCtrl.getInstance().getClassroomInfo(); }

    public ArrayList<ArrayList<String>> getGroupsInfo(){ return DomainCtrl.getInstance().getGroupInfo(); }

    public ArrayList<String> getAvailableRestrictions(){ return DomainCtrl.getInstance().getAvailableRestrictions(); }

    public ArrayList<String> getAppliedRestrictions(){ return DomainCtrl.getInstance().getAppliedRestrictions(); }

    public ArrayList<String> getGroupTypes(){ return DomainCtrl.getInstance().getGroupTypes(); }

    public TreeSet<String> getAllExtras(){ return DomainCtrl.getInstance().getAllExtras(); }

    public Boolean applyRestriction(String id, ArrayList<String> args){
        return DomainCtrl.getInstance().applyRestriction(id,args);
    }

    public Boolean deleteAppliedRestriction(String id) {
        return DomainCtrl.getInstance().deleteAppliedRestriction(id);
    }

    public void generate(){ DomainCtrl.getInstance().generateSchedule(); }

    public Boolean deleteSchedules(){ return DomainCtrl.getInstance().clearSavedSchedules(); }

    public void showSavedSchedules(){ DomainCtrl.getInstance().showSavedSchedules();}

    public Boolean importSchedules(String path){ return DomainCtrl.getInstance().importSchedules(path); }

    public Boolean isRestrictionEditable(String s) {
        return DomainCtrl.getInstance().isRestrictionEditable(s);
    }

    public Boolean updateScore(String res, int score){
        return DomainCtrl.getInstance().updateScore(res,score);
    }
}
