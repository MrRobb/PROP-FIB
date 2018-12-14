package Presentation;

import Domain.Subject;
import Domain.Subjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RestrictionView implements Initializable {
    @FXML private Button backToAction;
    @FXML private TableView<PairNumberRestriction> availableRestrictionsTable;
    @FXML private TableView<PairNumberRestriction> appliedRestrictionsTable;
    @FXML private Button applyRestriction;
    @FXML private Button deleteRestriction;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image image = new Image(getClass().getResourceAsStream("back.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        backToAction.setGraphic(imageView);

        applyRestriction.setDisable(true);

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


        ArrayList<String> appliedRestrictions = PresentationCtrl.getInstance().getAppliedRestrictions();
        ArrayList<PairNumberRestriction> list1 = new ArrayList<>();
        TableColumn<PairNumberRestriction,Integer> numRCol1 = new TableColumn<>("Number");
        TableColumn<PairNumberRestriction,String> nameRCol1 = new TableColumn<>("Restriction");
        TableColumn<PairNumberRestriction,CheckBox> prefCol = new TableColumn<>("Preference");
        appliedRestrictionsTable.getColumns().addAll(numRCol1,nameRCol1,prefCol);
        numRCol1.setCellValueFactory(new PropertyValueFactory<>("i"));
        nameRCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        prefCol.setCellValueFactory(new PropertyValueFactory<>("mandatory"));

        Integer j = 1;
        for(String r : appliedRestrictions){
            boolean e = PresentationCtrl.getInstance().isRestrictionEditable(r);
            CheckBox cb = new CheckBox();
            if(e){
                cb.setSelected(false);
                cb.setDisable(false);
            }
            else{
                cb.setSelected(false);
                cb.setDisable(true);
            }
            list1.add(new PairNumberRestriction(j,r,cb));
            j++;
        }
        ObservableList<PairNumberRestriction> data2 = FXCollections.observableArrayList(list1);
        appliedRestrictionsTable.setItems(data2);


    }

    public void BackPressed(ActionEvent event) throws IOException {
        Parent ViewParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
        Scene ViewScene = new Scene(ViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ViewScene);
        window.show();
    }


    public void tableCellClicked(){
        applyRestriction.setDisable(false);
    }

    public void applyRestrictionPressed(ActionEvent event) throws IOException{
        if(availableRestrictionsTable.getSelectionModel().getSelectedItem() != null){
            PairNumberRestriction p = availableRestrictionsTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ApplyingRestriction.fxml"));
            Parent ViewParent = loader.load();
            Scene ViewScene = new Scene(ViewParent);
            ApplyingRestriction controller = loader.getController();
            controller.initData(p);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ViewScene);
            window.show();
        }
    }

    public void deletePressed() {
        if(appliedRestrictionsTable.getSelectionModel().getSelectedItem() != null){
            PairNumberRestriction p = appliedRestrictionsTable.getSelectionModel().getSelectedItem();
            PresentationCtrl.getInstance().deleteAppliedRestriction(p.getName());
            appliedRestrictionsTable.getItems().remove(p);
        }
    }

}
