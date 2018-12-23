package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.*;
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

    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");



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

        numRCol.prefWidthProperty().bind(availableRestrictionsTable.widthProperty().multiply(0.09));
        nameRCol.prefWidthProperty().bind(availableRestrictionsTable.widthProperty().multiply(0.87));
        nameRCol.setResizable(false);
        numRCol.setResizable(false);
        numRCol.getStyleClass().add("cols");

        Integer k = 1;
        for(String r : availableRestrictions){
            list.add(new PairNumberRestriction(k,r));
            k++;
        }
        ObservableList<PairNumberRestriction> data1 = FXCollections.observableArrayList(list);
        availableRestrictionsTable.setItems(data1);

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<String> appliedRestrictions = PresentationCtrl.getInstance().getAppliedRestrictions();
        ArrayList<PairNumberRestriction> list1 = new ArrayList<>();
        TableColumn<PairNumberRestriction,Integer> numRCol1 = new TableColumn<>("Number");
        TableColumn<PairNumberRestriction,String> nameRCol1 = new TableColumn<>("Restriction");
        TableColumn<PairNumberRestriction,CheckBox> prefCol = new TableColumn<>("Preference");
        appliedRestrictionsTable.getColumns().addAll(numRCol1,nameRCol1,prefCol);
        numRCol1.setCellValueFactory(new PropertyValueFactory<>("i"));
        nameRCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        prefCol.setCellValueFactory(new PropertyValueFactory<>("mandatory"));

        numRCol1.prefWidthProperty().bind(availableRestrictionsTable.widthProperty().multiply(0.09));
        nameRCol1.prefWidthProperty().bind(availableRestrictionsTable.widthProperty().multiply(0.75));
        prefCol.prefWidthProperty().bind(availableRestrictionsTable.widthProperty().multiply(0.1));
        nameRCol1.setResizable(true);
        numRCol1.setResizable(false);
        prefCol.setResizable(false);
        numRCol1.getStyleClass().add("cols");
        prefCol.getStyleClass().add("cols");


        EventHandler eh = (EventHandler<ActionEvent>) event -> {
            if (event.getSource() instanceof CheckBox) {
                CheckBox chk = (CheckBox) event.getSource();
                if(chk.isSelected()) PresentationCtrl.getInstance().setAppliedRestriction(true,chk.getId());
                else PresentationCtrl.getInstance().setAppliedRestriction(false,chk.getId());
                List<PairNumberRestriction> listR = appliedRestrictionsTable.getItems();
                Collections.sort(listR);
                ObservableList<PairNumberRestriction> data = FXCollections.observableArrayList(listR);
                appliedRestrictionsTable.setItems(data);
            }
        };



        Integer j = 1;
        for(String r : appliedRestrictions){
            boolean e = PresentationCtrl.getInstance().isRestrictionEditable(r);
            CheckBox cb = new CheckBox();
            cb.setOnAction(eh);
            cb.setId(r);
            if(e){
                cb.setSelected(PresentationCtrl.getInstance().getAppliedRestriction(r));
                cb.setDisable(false);
            }
            else{
                cb.setSelected(false);
                cb.setDisable(true);
            }
            list1.add(new PairNumberRestriction(j,r,cb));
            j++;
        }

        Collections.sort(list1);
        for(int i = 0; i < list1.size(); ++i){
            list1.get(i).setI(i+1);
        }
        ObservableList<PairNumberRestriction> data2 = FXCollections.observableArrayList(list1);
        appliedRestrictionsTable.setItems(data2);




        appliedRestrictionsTable.setRowFactory(tv -> {
            TableRow<PairNumberRestriction> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                PairNumberRestriction rest = row.getItem();
                if (!row.isEmpty() && rest.getMandatory().isSelected()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        if(row.getItem().getMandatory().isSelected()) {
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                            event.consume();
                        }
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    PairNumberRestriction draggedRest = appliedRestrictionsTable.getItems().remove(draggedIndex);

                    int dropIndex ;

                    if (row.isEmpty() || row.getItem().getMandatory().isDisable()
                        || !row.getItem().getMandatory().isSelected()) {
                        dropIndex = appliedRestrictionsTable.getItems().size() ;
                    }
                    else {
                        dropIndex = row.getIndex();
                    }


                    appliedRestrictionsTable.getItems().add(dropIndex, draggedRest);

                    event.setDropCompleted(true);
                    appliedRestrictionsTable.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row ;
        });
    }

    public void BackPressed(ActionEvent event) throws IOException {
        // before going back, we update the scores of the preferences
        int score = 10;
        List<PairNumberRestriction> list = appliedRestrictionsTable.getItems();
        for(PairNumberRestriction p : list){

            String id = p.getName();
            boolean isMandatory = !p.getMandatory().isSelected();
            PresentationCtrl.getInstance().setMandatory(id, isMandatory);

            if (!isMandatory) {

                PresentationCtrl.getInstance().updateScore(id,score);
                if(score > 1) --score;
            }
        }

        Parent ViewParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
        Scene ViewScene = new Scene(ViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ViewScene);
        window.setTitle("Administrator panel");
        window.show();
    }


    public void tableCellClicked(){
        applyRestriction.setDisable(false);
    }

    public void tableCellClickedApplied(){
        if(appliedRestrictionsTable.getSelectionModel().getSelectedItem() != null) {
            PairNumberRestriction p = appliedRestrictionsTable.getSelectionModel().getSelectedItem();
            if (p.getMandatory().isDisable()) deleteRestriction.setDisable(true);
            else deleteRestriction.setDisable(false);
        }
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
            window.setTitle("Apply restriction");
            window.show();
        }
    }

    public void deletePressed() {
        if(appliedRestrictionsTable.getSelectionModel().getSelectedItem() != null){
            PairNumberRestriction p = appliedRestrictionsTable.getSelectionModel().getSelectedItem();
            PresentationCtrl.getInstance().deleteAppliedRestriction(p.getName());
            appliedRestrictionsTable.getItems().remove(p);
            List<PairNumberRestriction> list = appliedRestrictionsTable.getItems();
            Collections.sort(list);
            for(int i = 0; i < list.size(); ++i){
                list.get(i).setI(i+1);
            }
            ObservableList<PairNumberRestriction> data = FXCollections.observableArrayList(list);
            appliedRestrictionsTable.setItems(data);

        }
    }

}
