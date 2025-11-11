module com.example.oishiisushidesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires okhttp3;
    requires retrofit2.converter.gson;


    opens com.example.oishiisushidesktop to javafx.fxml;
    exports com.example.oishiisushidesktop;
}