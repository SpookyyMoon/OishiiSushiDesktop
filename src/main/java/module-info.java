module com.example.oishiisushidesktop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oishiisushidesktop to javafx.fxml;
    exports com.example.oishiisushidesktop;
}