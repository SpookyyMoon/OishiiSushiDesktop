package com.example.oishiisushidesktop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    ImageView mesaUno, mesaDos, mesaTres, mesaCuatro, mesaCinco;
    @FXML
    Pane bordeMesaUno, bordeMesaDos, bordeMesaTres, bordeMesaCuatro, bordeMesaCinco;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bordeMesaUno.setPickOnBounds(true);
        bordeMesaUno.setOnMouseClicked(Event -> {
            bordeMesaUno.getStyleClass().add("mesaSeleccionada");
            bordeMesaDos.getStyleClass().clear();
            bordeMesaTres.getStyleClass().clear();
            bordeMesaCuatro.getStyleClass().clear();
            bordeMesaCinco.getStyleClass().clear();
        });
        bordeMesaDos.setOnMouseClicked(Event -> {
            bordeMesaDos.getStyleClass().add("mesaSeleccionada");
            bordeMesaUno.getStyleClass().clear();
            bordeMesaTres.getStyleClass().clear();
            bordeMesaCuatro.getStyleClass().clear();
            bordeMesaCinco.getStyleClass().clear();
        });
        bordeMesaTres.setOnMouseClicked(Event -> {
            bordeMesaTres.getStyleClass().add("mesaSeleccionada");
            bordeMesaUno.getStyleClass().clear();
            bordeMesaDos.getStyleClass().clear();
            bordeMesaCuatro.getStyleClass().clear();
            bordeMesaCinco.getStyleClass().clear();
        });
        bordeMesaCuatro.setOnMouseClicked(Event -> {
            bordeMesaCuatro.getStyleClass().add("mesaSeleccionada");
            bordeMesaUno.getStyleClass().clear();
            bordeMesaDos.getStyleClass().clear();
            bordeMesaTres.getStyleClass().clear();
            bordeMesaCinco.getStyleClass().clear();
        });
        bordeMesaCinco.setOnMouseClicked(Event -> {
            bordeMesaCinco.getStyleClass().add("mesaSeleccionada");
            bordeMesaUno.getStyleClass().clear();
            bordeMesaDos.getStyleClass().clear();
            bordeMesaTres.getStyleClass().clear();
            bordeMesaCuatro.getStyleClass().clear();
        });
    }

    @FXML public void marcarMesaServida() {

    }

    @FXML public void verComandaMesa() {
    }

    @FXML public void marcarMesaVacia() {
    }
}
