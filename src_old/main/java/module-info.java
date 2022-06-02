module com.example.tpnote_eteinslalumiere {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.switchOffTheLight to javafx.fxml;
    exports com.example.switchOffTheLight;
    exports com.example.switchOffTheLight._main;
    opens com.example.switchOffTheLight._main to javafx.fxml;
}