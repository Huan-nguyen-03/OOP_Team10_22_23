package bomberman;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/qrabiloo", "root", "");
            System.out.println("done");
            return conn;
        } catch (Exception e) {
            System.out.println("non");
            return null;
        }
    }

    public static ObservableList<user> getDatauser() {
        Connection conn = ConnectDb();
        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from userhistory where userName = ?");
            pst.setString(1,BombermanGame.username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new user(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("timePlayed")),
                        Integer.parseInt(rs.getString("score")),
                        rs.getString("userName"),
                        rs.getString("timeStart"), rs.getString("status")  ));
            }
        } catch (Exception e) {
            System.out.println("Đần Độn Trần Thuật");
        }
        return list;
    }

    public static ObservableList<user> getDataAllUser() {
        Connection conn = ConnectDb();
        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from userhistory");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new user(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("timePlayed")),
                        Integer.parseInt(rs.getString("score")),
                        rs.getString("userName"),
                        rs.getString("timeStart"), rs.getString("status")  ));
            }
        } catch (Exception e) {
            System.out.println("Đần Độn Trần Thuật");
        }
        return list;
    }
}
