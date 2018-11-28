package Presentation;

import Domain.DomainCtrl;

import java.util.ArrayList;

public class PresentationCtrl {
    private static PresentationCtrl instance = null;

    private PresentationCtrl() {}

    public static PresentationCtrl getInstance() {
        if (instance == null) {
            instance = new PresentationCtrl();
        }
        return instance;
    }

    public ArrayList<String> getSubjects(){
        return DomainCtrl.getInstance().getSubjects();
    }
}
