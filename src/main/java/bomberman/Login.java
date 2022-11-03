package bomberman;

import bomberman.Sound.Sound;
import bomberman.graphics.Sprite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.sql.*;

import static bomberman.BombermanGame.HEIGHT;
import static bomberman.BombermanGame.WIDTH;

public class Login implements Initializable {
    @FXML
    private Button signUp;

    @FXML
    private Button btnok;

    @FXML
    private PasswordField txtpass;

    @FXML
    private TextField txtuname;

    @FXML
    private Label error;

    String uname;

    String pass;


    Connection con;
    PreparedStatement pst;

    ResultSet rs;

    public void handleButtonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnok) {
            if(logIn().equals("admin")) {
                try {
                    FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\End.fxml").toURI().toURL());
                    Parent root = loader.load();
                    BombermanGame.stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();;
                    BombermanGame.stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
                    BombermanGame.stage.show();
                    BombermanGame.username = uname;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if(logIn().equals("user")) {
                try {
                    FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\Menu.fxml").toURI().toURL());
                    Parent root = loader.load();
                    BombermanGame.stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();;
                    BombermanGame.stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
                    BombermanGame.stage.show();
                    BombermanGame.username = uname;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }


        }
        if (actionEvent.getSource() == signUp) {
            try {
                FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\signUp.fxml").toURI().toURL());
                Parent root = loader.load();
                BombermanGame.stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();;
                BombermanGame.stage.setScene(new Scene(root));
                BombermanGame.stage.show();
                BombermanGame.username = uname;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    public String logIn() {


        uname = txtuname.getText();
        pass = txtpass.getText();

        if (uname.equals("") && pass.equals(""))
        {
            error.setText( "Please enter your username and password");
            return "Blank";
        }
        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/qrabiloo", "root", "");

                pst = con.prepareStatement("select * from userbomber where username = ? and password = ?");

                pst.setString(1, uname);

                pst.setString(2, pass);

                rs = pst.executeQuery();

                if (rs.next()) {
                    if (rs.getBoolean("admin")) {
                        error.setText("Login Successfully");
                        System.out.println("Successfully");
                        BombermanGame.loginSuccess = true;
                        return "admin";
                    } else
                    {
                        error.setText("Login Successfully");
                        System.out.println("Successfully");
                        BombermanGame.loginSuccess = true;
                        return "user";
                    }
                } else {
                    error.setText("Invalid username or password");
                    txtuname.setText("");
                    txtpass.setText("");
                    txtuname.requestFocus();
                    System.err.println("Invalid username or password");
                    return "Failed";
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



}
