package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RestrictionView implements Initializable {
    @FXML private Button backToAction;
    @FXML private TableView<PairNumberRestriction> availableRestrictionsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image image = new Image(getClass().getResourceAsStream("back.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        backToAction.setGraphic(imageView);

        ArrayList<String> availableRestrictions = PresentationCtrl.getInstance().getAvailableRestrictions();
        ArrayList<PairNumberRestriction> list = new ArrayList<>();
        TableColumn<PairNumberRestriction,Integer> numRCol = new TableColumn<>("Number");
        TableColumn<PairNumberRestriction,String> nameRCol = new TableColumn<>("Restriction");
        availableRestrictionsTable.getColumns().addAll(numRCol,nameRCol);
        numRCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        nameRCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        Integer k = 1;
        for(String r : availableRestrictions){
            list.add(new PairNumberRestriction(k,r));
            k++;
        }
        ObservableList<PairNumberRestriction> data1 = FXCollections.observableArrayList(list);
        availableRestrictionsTable.setItems(data1);

    }

    public void BackPressed(ActionEvent event) throws IOException {
        Parent ViewParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
        Scene ViewScene = new Scene(ViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ViewScene);
        window.show();
    }
}
