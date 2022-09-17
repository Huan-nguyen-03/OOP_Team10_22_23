module bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens bomberman to javafx.fxml;
    exports bomberman;
}