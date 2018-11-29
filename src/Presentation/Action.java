package Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Action implements Initializable {
    @FXML private Button btnConsult;

    public void changeScreenButtonConsultPressed(ActionEvent event) throws IOException {
        Parent ConsultViewParent = FXMLLoader.load(getClass().getResource("ConsultingData.fxml"));
        Scene consultViewScene = new Scene(ConsultViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(consultViewScene);
        window.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
