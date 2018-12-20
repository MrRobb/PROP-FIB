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



public class ConsultingData implements Initializable {

    @FXML private TableView<PairNumberSubject> subjectTable;
    @FXML private TableView<PairNumberClassroom> classroomTable;
    @FXML private TableView<PairNumberGroup> groupTable;
    @FXML private Button backToAction;
    private String user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("back.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        backToAction.setGraphic(imageView);
        // Subjects information
        ArrayList<String> subjects = PresentationCtrl.getInstance().getSubjects();
        ArrayList<PairNumberSubject> list = new ArrayList<>();

        TableColumn<PairNumberSubject,Integer> numSCol = new TableColumn<>("Number");
        TableColumn<PairNumberSubject,String> nameSCol = new TableColumn<>("Subject");
        subjectTable.getColumns().addAll(numSCol,nameSCol);
        numSCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        nameSCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        numSCol.getStyleClass().add("cols");
        nameSCol.getStyleClass().add("cols");

        Integer j = 1;
        for(String s : subjects){
            list.add(new PairNumberSubject(j,s));
            j++;
        }
        ObservableList<PairNumberSubject> data = FXCollections.observableArrayList(list);
        subjectTable.setItems(data);


        // Classrooms information
        ArrayList<ArrayList<String>> classrooms = PresentationCtrl.getInstance().getClassroomInfo();
        ArrayList<PairNumberClassroom> list1 = new ArrayList<>();

        TableColumn<PairNumberClassroom,Integer> numCCol = new TableColumn<>("Number");
        TableColumn<PairNumberClassroom,String> nameCCol = new TableColumn<>("Classroom");
        TableColumn<PairNumberClassroom,String> extrasCCol = new TableColumn<>("Extras");
        classroomTable.getColumns().addAll(numCCol,nameCCol,extrasCCol);
        numCCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        nameCCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        extrasCCol.setCellValueFactory(new PropertyValueFactory<>("extras"));
        numCCol.getStyleClass().add("cols");
        nameCCol.getStyleClass().add("cols");
        extrasCCol.getStyleClass().add("cols");


        Integer k = 1;
        for(ArrayList<String> c : classrooms){
            String n = c.get(0);
            String e = c.get(1);
            list1.add(new PairNumberClassroom(k,n,e));
            k++;
        }
        ObservableList<PairNumberClassroom> data1 = FXCollections.observableArrayList(list1);
        classroomTable.setItems(data1);


        // Groups information
        ArrayList<ArrayList<String>> groups = PresentationCtrl.getInstance().getGroupsInfo();
        ArrayList<PairNumberGroup> list2 = new ArrayList<>();


        TableColumn<PairNumberGroup,Integer> numGCol = new TableColumn<>("Number");
        TableColumn<PairNumberGroup,String> subjGCol = new TableColumn<>("Subject");
        TableColumn<PairNumberGroup,String> nameGCol = new TableColumn<>("Group");
        TableColumn<PairNumberGroup,String> typesGCol = new TableColumn<>("Types");
        groupTable.getColumns().addAll(numGCol,subjGCol,nameGCol,typesGCol);
        numGCol.setCellValueFactory(new PropertyValueFactory<>("i"));
        subjGCol.setCellValueFactory(new PropertyValueFactory<>("subj"));
        nameGCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typesGCol.setCellValueFactory(new PropertyValueFactory<>("types"));
        numGCol.getStyleClass().add("cols");
        subjGCol.getStyleClass().add("cols");
        typesGCol.getStyleClass().add("cols");
        nameGCol.getStyleClass().add("cols");


        Integer l = 1;
        for(ArrayList<String> g : groups){
            String sname = g.get(0);
            String gname = g.get(1);
            String gtypes = g.get(2);
            list2.add(new PairNumberGroup(l,sname,gname,gtypes));
            l++;
        }
        ObservableList<PairNumberGroup> data2 = FXCollections.observableArrayList(list2);
        groupTable.setItems(data2);
    }


    public void changeScreenButtonBackPressed(ActionEvent event) throws IOException {
        if(user == "user"){
            Parent ViewParent = FXMLLoader.load(getClass().getResource("ActionUser.fxml"));
            Scene ViewScene = new Scene(ViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ViewScene);
            window.show();
        }
        else{
            Parent ViewParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
            Scene ViewScene = new Scene(ViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ViewScene);
            window.show();
        }

    }

    public void initData(String u){
        user = u;
    }

}
