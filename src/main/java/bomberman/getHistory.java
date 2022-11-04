package bomberman;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class getHistory implements Initializable {

    @FXML
    private TableView<user> tableH;

    @FXML
    private TableColumn<user, Integer> id;

    @FXML
    private TableColumn<user, Integer> score;


    @FXML
    private TableColumn<user, Integer> tp;

    @FXML
    private TableColumn<user, String> ts;

    @FXML
    private TableColumn<user, String> un;

    ObservableList<user> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<user, Integer>("id"));
        un.setCellValueFactory(new PropertyValueFactory<user, String>("un"));
        ts.setCellValueFactory(new PropertyValueFactory<user, String>("ts"));
        tp.setCellValueFactory(new PropertyValueFactory<user, Integer>("tp"));
        score.setCellValueFactory(new PropertyValueFactory<user, Integer>("score"));

        listM = mysqlconnect.getDatauser();
        tableH.setItems(listM);

    }
}
