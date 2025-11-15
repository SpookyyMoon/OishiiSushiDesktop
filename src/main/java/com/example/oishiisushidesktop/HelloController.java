package com.example.oishiisushidesktop;

import com.example.oishiisushidesktop.adaptadores.ApiAdapter;
import com.example.oishiisushidesktop.entidades.Comandas;
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
    ImageView mesaUno, mesaDos, mesaTres, mesaCuatro, mesaCinco, botonCerrarComanda;
    @FXML
    Pane bordeMesaUno, bordeMesaDos, bordeMesaTres, bordeMesaCuatro, bordeMesaCinco, panelOscurecer, panelComanda;

    List<Mesas> listaMesas;
    Mesas mesaSeleccionada;
    List<Comandas> listaComandas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Call<List<Mesas>> call = ApiAdapter.getApiService().getMesas();
        call.enqueue(this);
    }

    @FXML public void marcarMesaServida() {
        if (mesaSeleccionada == null) return;
        if (mesaSeleccionada.ocupadaMesa && !mesaSeleccionada.comandasMesa.isEmpty() && !mesaSeleccionada.comandasMesa.getLast().atendidaComanda) {
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
    }

    @FXML public void verComandaMesa() {
        panelOscurecer.setVisible(true);
        panelComanda.setVisible(true);
        panelOscurecer.toFront();
        panelComanda.toFront();
        botonCerrarComanda.setOnMouseClicked(mouseEvent -> {
            panelOscurecer.setVisible(false);
            panelComanda.setVisible(false);
            panelOscurecer.toBack();
            panelComanda.toBack();
        });

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

    public void seleccionMesa(int mesaSeleccion) {
        bordeMesaUno.getStyleClass().remove("mesaSeleccionada");
        bordeMesaDos.getStyleClass().remove("mesaSeleccionada");
        bordeMesaTres.getStyleClass().remove("mesaSeleccionada");
        bordeMesaCuatro.getStyleClass().remove("mesaSeleccionada");
        bordeMesaCinco.getStyleClass().remove("mesaSeleccionada");

        switch (mesaSeleccion) {
            case 0:
                bordeMesaUno.getStyleClass().add("mesaSeleccionada");
                break;
            case 1:
                bordeMesaDos.getStyleClass().add("mesaSeleccionada");
                break;
            case 2:
                bordeMesaTres.getStyleClass().add("mesaSeleccionada");
                break;
            case 3:
                bordeMesaCuatro.getStyleClass().add("mesaSeleccionada");
                break;
            case 4:
                bordeMesaCinco.getStyleClass().add("mesaSeleccionada");
                break;

        }

        mesaSeleccionada = listaMesas.get(mesaSeleccion);
    }

    @Override
    public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
        if (response.isSuccessful()) {
            listaMesas = response.body();
            if (listaMesas != null && !listaMesas.isEmpty()) {
                System.out.println(("onResponse mesas: " + "Tamaño de la lista de mesas -> " + listaMesas.size()));
            }

            for (Mesas mesa : listaMesas) {
                if (mesa.ocupadaMesa) {
                    marcarMesaOcupada(mesa);
                }
                else{
                    marcarMesaVacia(mesa);
                }
            }

            bordeMesaUno.setPickOnBounds(true);
            bordeMesaUno.setOnMouseClicked(mouseEvent -> {
                seleccionMesa(0);
            });
            bordeMesaDos.setPickOnBounds(true);
            bordeMesaDos.setOnMouseClicked(mouseEvent -> {
                seleccionMesa(1);
            });
            bordeMesaTres.setPickOnBounds(true);
            bordeMesaTres.setOnMouseClicked(mouseEvent -> {
                seleccionMesa(2);
            });
            bordeMesaCuatro.setPickOnBounds(true);
            bordeMesaCuatro.setOnMouseClicked(mouseEvent -> {
                seleccionMesa(3);
            });
            bordeMesaCinco.setPickOnBounds(true);
            bordeMesaCinco.setOnMouseClicked(mouseEvent -> {
                seleccionMesa(4);
            });
        }
    }

    @Override
    public void onFailure(Call<List<Mesas>> call, Throwable throwable) {

    }
}
