package Presentation;


import Domain.In;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class ConsultingData implements Initializable {

    @FXML private TableView<PairNumberSubject> subjectTable;
    @FXML private TableView<PairNumberClassroom> classroomTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> subjects = PresentationCtrl.getInstance().getSubjects();
        ArrayList<PairNumberSubject> list = new ArrayList<>();

        TableColumn<PairNumberSubject,Integer> numSCol = new TableColumn<>("Number");
        TableColumn<PairNumberSubject,String> nameSCol = new TableColumn<>("Subject");
        subjectTable.getColumns().addAll(numSCol,nameSCol);
        numSCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        nameSCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        Integer j = 1;
        for(String s : subjects){
            list.add(new PairNumberSubject(j,s));
            j++;
        }
        ObservableList<PairNumberSubject> data = FXCollections.observableArrayList(list);
        subjectTable.setItems(data);



    }

    public void changeScreenButtonBackPressed(ActionEvent event) throws IOException {
        Parent ConsultViewParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
        Scene consultViewScene = new Scene(ConsultViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(consultViewScene);
        window.show();
    }
}
