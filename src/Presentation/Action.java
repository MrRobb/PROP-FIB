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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Action implements Initializable {
    @FXML private Button btnConsult;
    @FXML private Button btnGenerate;
    @FXML private Button btnLogOut;
    @FXML private Button btnModifyRestrictions;
    @FXML private Button btnShowSavesSchedules;
    @FXML private Button btnDeleteSchedules;

    public void ButtonConsultPressed(ActionEvent event) throws IOException {
        Parent ConsultViewParent = FXMLLoader.load(getClass().getResource("ConsultingData.fxml"));
        Scene consultViewScene = new Scene(ConsultViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(consultViewScene);
        window.show();
    }

    public void ButtonDeleteSchedulesPressed(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Do you really want to delete all schedules?");
        alert.setContentText("This action is not reversible, all data will be lost.");
        alert.showAndWait();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
