package bomberman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderMenu implements Initializable {

    @FXML
    private ToggleGroup bomb;

    @FXML
    private ToggleGroup map;

    @FXML
    private ToggleGroup speed;

    @FXML
    void back(MouseEvent event) {

    }

    @FXML
    void five(MouseEvent event) {

    }

    @FXML
    void four(MouseEvent event) {

    }

    @FXML
    void level1(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\mapText.fxml").toURI().toURL());
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();;
        stage.setScene(new Scene(root, 800,500));
        stage.show();
    }

    @FXML
    void level2(MouseEvent event) {

    }

    @FXML
    void level3(MouseEvent event) {

    }

    @FXML
    void level4(MouseEvent event) {

    }

    @FXML
    void level5(MouseEvent event) {

    }

    @FXML
    void one(MouseEvent event) {

    }

    @FXML
    void save(MouseEvent event) {

    }

    @FXML
    void three(MouseEvent event) {

    }

    @FXML
    void two(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
