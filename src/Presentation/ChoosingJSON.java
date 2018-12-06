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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoosingJSON implements Initializable{
    public Button fileMenuButton;


    public void pressOpenButton(Event event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        String path = file.getPath();

        if (PresentationCtrl.getInstance().produce(path)) {
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
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(actionViewScene);
            window.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error!");
            alert.setContentText("Error while loading JSON. Make sure you have selected the right file.");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
