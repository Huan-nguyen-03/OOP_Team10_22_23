package bomberman;

import bomberman.entities.Entity;
import bomberman.entities.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    @FXML
    private TextArea mapTxt;

    @FXML
    void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\orderMenu.fxml").toURI().toURL());
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();;
        stage.setScene(new Scene(root, 800,500));
        stage.show();
    }

    @FXML
    void saveTxt(MouseEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Map m = new Map();
        try {
            m.loadMap(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < Map.row; i++) {
            for (int j = 0; j < Map.column; j++) {
                mapTxt.appendText(String.valueOf(Map.map[i][j]));
                System.out.println(mapTxt);
            }
            mapTxt.appendText("\n");
        }


    }

}
