package bomberman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    }

}
