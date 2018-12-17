package Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionUser implements Initializable {
    @FXML private Button btnConsult;
    @FXML private Button btnGenerate;
    @FXML private Button btnLogOut;
    @FXML private Button btnModifyRestrictions;
    @FXML private Button btnShowSavedSchedules;
    @FXML private Button btnDeleteSchedules;
    @FXML private Button btnImportSchedules;
    @FXML private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.getStyleClass().add("title");
    }

    public void showSavedSchedulesPressed(){
        PresentationCtrl.getInstance().showSavedSchedules();
    }

    public void logOutPressed(ActionEvent event) throws IOException {
        Parent ViewParent = FXMLLoader.load(getClass().getResource("UserSelection.fxml"));
        Scene ViewScene = new Scene(ViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ViewScene);
        window.show();
    }

    public Boolean importPressed(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        String path = file.getPath();
        if(PresentationCtrl.getInstance().importSchedules(path)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Successful");
            alert.setContentText("File imported.");
            alert.showAndWait();
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Could not import file. Make sure that you are importing a valid file.");
            alert.showAndWait();
            return false;
        }
    }

    public void ButtonConsultPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConsultingData.fxml"));
        Parent ViewParent = loader.load();
        Scene ViewScene = new Scene(ViewParent);
        ConsultingData controller = loader.getController();
        controller.initData("user");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ViewScene);
        window.show();
    }
}
