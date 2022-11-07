package bomberman;

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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import static bomberman.BombermanGame.HEIGHT;
import static bomberman.BombermanGame.WIDTH;


public class LeaderBoard implements Initializable {

    @FXML
    private Button returnButton;

    @FXML
    private Label highestscore;

    @FXML
    private Button btnLeader;
    @FXML
    private Label rank;

    @FXML
    private Label top1;

    @FXML
    private Label top1Score;

    @FXML
    private Label top2;

    @FXML
    private Label top2Score;

    @FXML
    private Label top3;

    @FXML
    private  Label top3Score;

    public String score;
    public String ranks;
    public String acc;




    public void handleButtonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == returnButton) {
            if (!BombermanGame.admin) {
                try {
                    FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\Menu.fxml").toURI().toURL());
                    Parent root = loader.load();
                    BombermanGame.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    ;
                    BombermanGame.stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
                    BombermanGame.stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else {
                try {
                    FXMLLoader loader = new FXMLLoader(new File("src\\main\\java\\bomberman\\adminMenu.fxml").toURI().toURL());
                    Parent root = loader.load();
                    BombermanGame.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    ;
                    BombermanGame.stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
                    BombermanGame.stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }
    }


    public String updateLeaderBoard()
     {
         Connection con;
         PreparedStatement pst;
         ResultSet rs;
         String query = "SELECT * from userbomber order by highestScore DESC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/qrabiloo", "root", "");
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        int dem = 1;
        final int mot = 1;
        final int hai = 2;
        final int ba = 3;
        while (true) {
            System.out.println("NOn");
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (Objects.equals(dem, mot)) {
                System.out.println("HEHE");

                try {
                    top1.setText(rs.getString("userName"));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    top1Score.setText(String.valueOf(rs.getInt("highestScore")));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (Objects.equals(dem, hai)) {
                try {
                    top2Score.setText(String.valueOf(rs.getInt("highestScore")));

                    top2.setText(rs.getString("userName"));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
            if (Objects.equals(dem, ba)) {
                try {
                    top3Score.setText(String.valueOf(rs.getInt("highestScore")));

                    top3.setText(rs.getString("username"));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            try {
                if (rs.getString("userName").equals(BombermanGame.username)) {
                    rank.setText(String.valueOf(dem));
                    highestscore.setText(String.valueOf(rs.getInt("highestScore")));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dem++;
        }
        dem = 1;
        return "true";
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateLeaderBoard();
    }


}
