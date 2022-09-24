module bomberman.Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens bomberman.Main to javafx.fxml;
    exports bomberman.Main;
}