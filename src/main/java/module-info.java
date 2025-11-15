module com.example.oishiisushidesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires okhttp3;
    requires java.sql;
    requires retrofit2.converter.gson;

    opens com.example.oishiisushidesktop to javafx.fxml;
    opens com.example.oishiisushidesktop.entidades;

    exports com.example.oishiisushidesktop;
    exports com.example.oishiisushidesktop.entidades;
}