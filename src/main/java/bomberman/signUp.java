package bomberman;

import bomberman.graphics.Sprite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static bomberman.BombermanGame.HEIGHT;
import static bomberman.BombermanGame.WIDTH;




public class signUp implements Initializable {
    @FXML
    private ImageView back;

    @FXML
    private ImageView signUp;

    @FXML
    private TextField userFN;

    @FXML
    private TextField userLN;

    @FXML
    private PasswordField userP;

    @FXML
    private PasswordField userRP;

    @FXML
    private TextField userU;
    @FXML
    private Label error;

    @FXML
    private DatePicker userBirth;

    Connection con;
    PreparedStatement pst;

    ResultSet rs;

    String query = "insert into userbomber(firstName, lastName, username, password, dateOfBirth, timeRegister) values (?,?,?,?,?,?)";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonAction(MouseEvent actionEvent) {
        if (actionEvent.getSource() == back) {

            try {
                FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\Login.fxml").toURI().toURL());
                Parent root = loader.load();
                BombermanGame.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                ;
                BombermanGame.stage.setScene(new Scene(root));
                BombermanGame.stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (actionEvent.getSource() == signUp) {
            if (signUp().equals("Success")) {

            }
        }
    }
    public String signUp() {


        String fn = userFN.getText();
        String ln = userLN.getText();
        String un = userU.getText();
        String pass = userP.getText();
        String repass = userRP.getText();

        String bday = userBirth.getValue().toString();
        System.out.println(bday);

        if (fn.equals(""))
        {
            error.setText( "Please enter your first name");
            userFN.requestFocus();
            return "Blank";
        }
        else if (ln.equals(""))
        {
            error.setText( "Please enter your last name");
            userLN.requestFocus();
            return "Blank";
        }
        else if (un.equals(""))
        {
            error.setText( "Please enter your username");
            userU.requestFocus();
            return "Blank";
        }
        else if (pass.equals(""))
        {
            error.setText( "Please enter your password");
            userP.requestFocus();
            return "Blank";
        }
        else if (repass.equals(""))
        {
            error.setText( "Please enter retype password");
            userRP.requestFocus();
            return "Blank";
        }
        else if(!pass.equals(repass))
        {
            error.setText( "Please retype your repassword");
            userP.setText("");
            userRP.setText("");
            userP.requestFocus();
            return "blank";
        }
        else if (bday.equals(""))
        {
            error.setText( "Please enter your birthday");
            userBirth.requestFocus();
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

                pst = con.prepareStatement(query);

                pst.setString(1, fn);

                pst.setString(2, ln);

                pst.setString(3, un);

                pst.setString(4, pass);

                pst.setString(5, bday);

                LocalDateTime timeRegister = LocalDateTime.now();

                pst.setString(6, String.valueOf(timeRegister));


                if (pst.executeUpdate() > 0) {
                    error.setText("SignUp Successfully");
                    System.out.println("Successfully");
                    return "Success";
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "failed";
    }


}
