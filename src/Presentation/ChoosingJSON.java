package Presentation;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoosingJSON implements Initializable{
    public SplitMenuButton fileMenuButton;
    public MenuItem btnFIBComplete;
    public MenuItem btnFIBMand;

    public void pressFIBcompletedButton(Event event) throws IOException {
        if(PresentationCtrl.getInstance().produce(2)){
            int s = PresentationCtrl.getInstance().getNumberOfSubjects();
            int g = PresentationCtrl.getInstance().getNumberOfGroups();
            int c = PresentationCtrl.getInstance().getNumberOfClassrooms();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("File imported successfully");
            alert.setContentText(s + " subjects, " + g + " groups and " + c + " classrooms generated.");
            alert.showAndWait();

            Parent ActionParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
            Scene actionViewScene = new Scene(ActionParent);
            Stage window = (Stage) fileMenuButton.getScene().getWindow();
            window.setScene(actionViewScene);
            window.show();
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Could not load file");
            alert.showAndWait();
        }


    }
    public void pressFIBmandButton(Event event) throws IOException {
        if (PresentationCtrl.getInstance().produce(1)) {
            int s = PresentationCtrl.getInstance().getNumberOfSubjects();
            int g = PresentationCtrl.getInstance().getNumberOfGroups();
            int c = PresentationCtrl.getInstance().getNumberOfClassrooms();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("File imported successfully");
            alert.setContentText(s + " subjects, " + g + " groups and " + c + " classrooms generated.");
            alert.showAndWait();

            Parent ActionParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
            Scene actionViewScene = new Scene(ActionParent);
            Stage window = (Stage) fileMenuButton.getScene().getWindow();
            window.setScene(actionViewScene);
            window.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Could not load file");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
