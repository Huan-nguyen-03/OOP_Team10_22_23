module bomberman{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens bomberman to javafx.fxml;
    exports bomberman;

}