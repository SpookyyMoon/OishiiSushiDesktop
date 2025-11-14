package com.example.oishiisushidesktop;

import com.example.oishiisushidesktop.adaptadores.ApiAdapter;
import com.example.oishiisushidesktop.entidades.Mesas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable, Callback<List<Mesas>> {
    @FXML
    ImageView mesaUno, mesaDos, mesaTres, mesaCuatro, mesaCinco;
    @FXML
    Pane bordeMesaUno, bordeMesaDos, bordeMesaTres, bordeMesaCuatro, bordeMesaCinco;

    List<Mesas> listaMesas;
    Mesas mesaSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Call<List<Mesas>> call = ApiAdapter.getApiService().getMesas();
        call.enqueue(this);

        bordeMesaUno.setPickOnBounds(true);
        bordeMesaUno.setOnMouseClicked(Event -> {
            bordeMesaUno.getStyleClass().add("mesaSeleccionada");
            bordeMesaDos.getStyleClass().clear();
            bordeMesaTres.getStyleClass().clear();
            bordeMesaCuatro.getStyleClass().clear();
            bordeMesaCinco.getStyleClass().clear();
            mesaSeleccionada = listaMesas.getFirst();
        });
        bordeMesaDos.setOnMouseClicked(Event -> {
            bordeMesaDos.getStyleClass().add("mesaSeleccionada");
            bordeMesaUno.getStyleClass().clear();
            bordeMesaTres.getStyleClass().clear();
            bordeMesaCuatro.getStyleClass().clear();
            bordeMesaCinco.getStyleClass().clear();
            mesaSeleccionada = listaMesas.get(1);
        });
        bordeMesaTres.setOnMouseClicked(Event -> {
            bordeMesaTres.getStyleClass().add("mesaSeleccionada");
            bordeMesaUno.getStyleClass().clear();
            bordeMesaDos.getStyleClass().clear();
            bordeMesaCuatro.getStyleClass().clear();
            bordeMesaCinco.getStyleClass().clear();
            mesaSeleccionada = listaMesas.get(2);
        });
        bordeMesaCuatro.setOnMouseClicked(Event -> {
            bordeMesaCuatro.getStyleClass().add("mesaSeleccionada");
            bordeMesaUno.getStyleClass().clear();
            bordeMesaDos.getStyleClass().clear();
            bordeMesaTres.getStyleClass().clear();
            bordeMesaCinco.getStyleClass().clear();
            mesaSeleccionada = listaMesas.get(3);
        });
        bordeMesaCinco.setOnMouseClicked(Event -> {
            bordeMesaCinco.getStyleClass().add("mesaSeleccionada");
            bordeMesaUno.getStyleClass().clear();
            bordeMesaDos.getStyleClass().clear();
            bordeMesaTres.getStyleClass().clear();
            bordeMesaCuatro.getStyleClass().clear();
            mesaSeleccionada = listaMesas.get(4);
        });
    }

    @FXML public void marcarMesaServida() {
        Image mesaServidaImagen = new Image(getClass().getResourceAsStream("/com/example/oishiisushidesktop/raw/mesaServida.png"));
        switch (mesaSeleccionada.numeroMesa) {
            case 1:
                mesaUno.setImage(mesaServidaImagen);
                break;
            case 2:
                mesaDos.setImage(mesaServidaImagen);
                break;
            case 3:
                mesaTres.setImage(mesaServidaImagen);
                break;
            case 4:
                mesaCuatro.setImage(mesaServidaImagen);
                break;
            case 5:
                mesaCinco.setImage(mesaServidaImagen);
                break;
            default:
                break;
        }
    }

    public void marcarMesaOcupada(Mesas mesa) {
        Image mesaOcupadaImagen = new Image(getClass().getResourceAsStream("/com/example/oishiisushidesktop/raw/mesaOcupada.png"));
        switch (mesa.numeroMesa) {
            case 1:
                mesaUno.setImage(mesaOcupadaImagen);
                break;
            case 2:
                mesaDos.setImage(mesaOcupadaImagen);
                break;
            case 3:
                mesaTres.setImage(mesaOcupadaImagen);
                break;
            case 4:
                mesaCuatro.setImage(mesaOcupadaImagen);
                break;
            case 5:
                mesaCinco.setImage(mesaOcupadaImagen);
                break;
            default:
                break;
        }
    }

    @FXML public void verComandaMesa() {
    }

    @FXML public void marcarMesaVacia(Mesas mesa) {
        Image mesaVaciaImagen = new Image(getClass().getResourceAsStream("/com/example/oishiisushidesktop/raw/mesaLibre.png"));
        switch (mesa.numeroMesa) {
            case 1:
                mesaUno.setImage(mesaVaciaImagen);
                break;
            case 2:
                mesaDos.setImage(mesaVaciaImagen);
                break;
            case 3:
                mesaTres.setImage(mesaVaciaImagen);
                break;
            case 4:
                mesaCuatro.setImage(mesaVaciaImagen);
                break;
            case 5:
                mesaCinco.setImage(mesaVaciaImagen);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
        if (response.isSuccessful()) {
            listaMesas = response.body();
            if (listaMesas != null && !listaMesas.isEmpty()) {
                System.out.println(("onResponse mesas: " + "Tamaño de la lista de mesas -> " + listaMesas.size()));
            }
            for (int i = 0; i < listaMesas.size(); i++) {
                if(listaMesas.get(i).ocupadaMesa == true ) {
                    marcarMesaOcupada(listaMesas.get(i));
                }
                else {
                    marcarMesaVacia(listaMesas.get(i));
                }
            }
        }
    }

    @Override
    public void onFailure(Call<List<Mesas>> call, Throwable throwable) {

    }
}
