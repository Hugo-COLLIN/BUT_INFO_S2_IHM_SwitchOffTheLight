module com.example.tpnote_eteinslalumiere {
    requires javafx.controls;
    requires javafx.fxml;


    opens example.switchOffTheLight to javafx.fxml;
    exports example.switchOffTheLight;
}