package bomberman;

import bomberman.graphics.Sprite;
import javafx.collections.ObservableList;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static bomberman.BombermanGame.*;


public class getAllHistory implements Initializable {

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

    @FXML
    private TableColumn<user, String> status;

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
        status.setCellValueFactory(new PropertyValueFactory<user, String>("status"));

        listM = mysqlconnect.getDataAllUser();
        tableH.setItems(listM);

    }

    public void backToMenu(javafx.event.ActionEvent event) throws IOException {
        if (!admin) {
            FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\Menu.fxml").toURI().toURL());
            Parent root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\adminMenu.fxml").toURI().toURL());
            Parent root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
            stage.show();
        }
    }
}
