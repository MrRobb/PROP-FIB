package Presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class UserSelection implements Initializable {
    @FXML private RadioButton user;
    @FXML private RadioButton admin;
    @FXML private Button login;
    @FXML private ToggleGroup radioGroup;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setDisable(true);
        user.setToggleGroup(radioGroup);
        admin.setToggleGroup(radioGroup);
    }

    public void radioSelected(){
        login.setDisable(false);
    }


    public void loginPressed(ActionEvent event) throws IOException {
        String op = radioGroup.getSelectedToggle().toString();
        if(op.equals("RadioButton[id=admin, styleClass=radio-button]' Administrator'")){
            Parent ViewParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
            Scene ViewScene = new Scene(ViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ViewScene);
            window.show();
        }
        else {
            Parent ViewParent = FXMLLoader.load(getClass().getResource("ActionUser.fxml"));
            Scene ViewScene = new Scene(ViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(ViewScene);
            window.show();
        }

    }

    public void exit(){
        System.exit(0);
    }
}
