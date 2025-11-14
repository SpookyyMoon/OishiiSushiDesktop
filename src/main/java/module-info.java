module com.example.oishiisushidesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires okhttp3;
    requires retrofit2.converter.gson;
    requires java.sql;

    opens com.example.oishiisushidesktop to javafx.fxml;

    opens com.example.oishiisushidesktop.entidades to com.google.gson;

    exports com.example.oishiisushidesktop;
    exports com.example.oishiisushidesktop.entidades;
}