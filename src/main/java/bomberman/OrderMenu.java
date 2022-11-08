package bomberman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    private RadioButton bomb1;

    @FXML
    private RadioButton bomb2;

    @FXML
    private RadioButton bomb3;

    @FXML
    private RadioButton bomb4;

    private int bombNum = 1;

    @FXML
    private ToggleGroup map;

    @FXML
    private RadioButton max1;

    @FXML
    private RadioButton max2;

    @FXML
    private RadioButton max3;

    @FXML
    private RadioButton max4;

    @FXML
    private RadioButton max5;

    private int maxLevel = 2;

    @FXML
    private Label setDefault;

    @FXML
    private ToggleGroup speed;

    @FXML
    private RadioButton speed1;

    @FXML
    private RadioButton speed2;

    @FXML
    private RadioButton speed3;

    private int speedUp = 2;

    @FXML
    private Label lv1;

    @FXML
    private Label lv2;

    @FXML
    private Label lv3;

    @FXML
    private Label lv4;

    @FXML
    private Label lv5;

    @FXML
    private Label error;


    @FXML
    void back(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\adminMenu.fxml").toURI().toURL());
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();;
        stage.setScene(new Scene(root, 800,500));
        stage.show();
    }



    @FXML
    void five(MouseEvent event) {
        lv1.setText("Level 1");
        lv2.setText("Level 2");
        lv3.setText("Level 3");
        lv4.setText("Level 4");
        lv5.setText("Level 5");
    }

    @FXML
    void four(MouseEvent event) throws IOException{
        lv1.setText("Level 1");
        lv2.setText("Level 2");
        lv3.setText("Level 3");
        lv4.setText("Level 4");
        lv5.setText("");
    }

    @FXML
    void level1(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\mapText.fxml").toURI().toURL());
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        lv1.setText("Level 1");
        lv2.setText("");
        lv3.setText("");
        lv4.setText("");
        lv5.setText("");
    }

    @FXML
    void save(MouseEvent event) {
         if (speed1.isSelected()) {
             speedUp = 1;
         }
         if (speed2.isSelected()) {
             speedUp = 2;
         }
         if (speed3.isSelected()) {
             speedUp = 3;
         }

         if (bomb1.isSelected()) {
             bombNum = 1;
         }
        if (bomb2.isSelected()) {
            bombNum = 2;
        }
        if (bomb3.isSelected()) {
            bombNum = 3;
        }
        if (bomb4.isSelected()) {
            bombNum = 4;
        }

        if (max1.isSelected()) {
            maxLevel = 1;
        }
        if (max2.isSelected()) {
            maxLevel = 2;
        }
        if (max3.isSelected()) {
            maxLevel = 3;
        }
        if (max4.isSelected()) {
            maxLevel = 4;
        }
        if (max5.isSelected()) {
            maxLevel = 5;
        }


        BombermanGame.MAX_BOMB_DEFAULT = bombNum;
        BombermanGame.VELOCITY_DEFAULT = speedUp;
        BombermanGame.maxLevel = maxLevel;
        error.setText("Save Successfully");

    }

    @FXML
    void three(MouseEvent event) {
        lv1.setText("Level 1");
        lv2.setText("Level 2");
        lv3.setText("Level 3");
        lv4.setText("");
        lv5.setText("");
    }

    @FXML
    void two(MouseEvent event) {
        lv1.setText("Level 1");
        lv2.setText("Level 2");
        lv3.setText("");
        lv4.setText("");
        lv5.setText("");
    }

    @FXML
    void setDefault(MouseEvent event) {
        BombermanGame.MAX_BOMB_DEFAULT = 1;
        BombermanGame.VELOCITY_DEFAULT = 2;
        BombermanGame.maxLevel = 2;
        error.setText("Set default Successfully");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
