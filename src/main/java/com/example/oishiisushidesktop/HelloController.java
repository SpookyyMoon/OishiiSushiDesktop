package com.example.oishiisushidesktop;

import com.example.oishiisushidesktop.adaptadores.ApiAdapter;
import com.example.oishiisushidesktop.entidades.Comandas;
import com.example.oishiisushidesktop.entidades.Mesas;
import com.example.oishiisushidesktop.entidades.Platos;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.text.Text;


public class HelloController implements Initializable, Callback<List<Mesas>> {
    @FXML
    ImageView mesaUno, mesaDos, mesaTres, mesaCuatro, mesaCinco, botonCerrarComanda,botonCerrarAlertaMesaServida;
    @FXML
    Pane bordeMesaUno, bordeMesaDos, bordeMesaTres, bordeMesaCuatro, bordeMesaCinco, panelOscurecer, panelComanda, alertaMesaServida;
    @FXML
    VBox comandasContenedor;
    @FXML
    Text numeroMesaComandas;

    List<Mesas> listaMesas;
    Mesas mesaSeleccionada;
    List<Comandas> listaComandas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Call<List<Mesas>> call = ApiAdapter.getApiService().getMesas();
        call.enqueue(this);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> {
                    recargarDB();
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        botonCerrarAlertaMesaServida.setOnMouseClicked(mouseEvent -> {
            panelOscurecer.setVisible(false);
            alertaMesaServida.setVisible(false);
        });

        botonCerrarComanda.setOnMouseClicked(mouseEvent -> {
            panelOscurecer.setVisible(false);
            panelComanda.setVisible(false);
        });
    }

    @FXML public void marcarMesaServida() {
        comprobarComandaPendiente(mesaSeleccionada);
    }

    @FXML public void verComandaMesa() {
        panelOscurecer.setVisible(true);
        panelComanda.setVisible(true);
        mostrarComandasMesa(mesaSeleccionada);
    }

    public void marcarMesaVacia(Mesas mesa) {
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

    public void mostrarComandasMesa(Mesas mesa) {
        numeroMesaComandas.setText("Mesa " + mesa.numeroMesa);
        comandasContenedor.getChildren().clear();

        for (Comandas c : listaComandas) {
            if (c.numeroMesa == mesa.numeroMesa && !c.atendidaComanda) {
                for (Platos p : c.pedidoPlatos) {
                    Text t = new Text(p.nombrePlato);
                    t.setStyle("-fx-font-size: 22; -fx-fill: black;");
                    comandasContenedor.getChildren().add(t);
                }
            }
        }
    }

    public void marcarMesaPendiente(Mesas mesa) {
        Image mesaPendienteImagen = new Image(getClass().getResourceAsStream("/com/example/oishiisushidesktop/raw/mesaPendiente.png"));
        switch (mesa.numeroMesa) {
            case 1:
                mesaUno.setImage(mesaPendienteImagen);
                break;
            case 2:
                mesaDos.setImage(mesaPendienteImagen);
                break;
            case 3:
                mesaTres.setImage(mesaPendienteImagen);
                break;
            case 4:
                mesaCuatro.setImage(mesaPendienteImagen);
                break;
            case 5:
                mesaCinco.setImage(mesaPendienteImagen);
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

    public void marcarComoServida(Mesas mesa) {
        Image mesaServidaImagen = new Image(
                getClass().getResourceAsStream("/com/example/oishiisushidesktop/raw/mesaServida.png")
        );

        switch (mesa.numeroMesa) {
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
        }

        for (Comandas comanda : listaComandas) {
            if (comanda.numeroMesa == mesa.numeroMesa && !comanda.atendidaComanda) {
                comanda.atendidaComanda = true;

                ApiAdapter.getApiService().updateComandas(comanda._id, comanda).enqueue(new Callback<Comandas>() {
                    @Override
                    public void onResponse(Call<Comandas> call, Response<Comandas> response) {
                    }

                    @Override
                    public void onFailure(Call<Comandas> call, Throwable t) {
                    }
                });
            }
        }
    }

    public void comprobarComandaPendiente(Mesas mesa) {
        ApiAdapter.getApiService().getComandas().enqueue(new Callback<List<Comandas>>() {
            @Override
            public void onResponse(Call<List<Comandas>> call, Response<List<Comandas>> response) {
                if (response.isSuccessful()) {
                    boolean comandaPendiente = false;

                    for (Comandas c : response.body()) {
                        if (c.numeroMesa == mesa.numeroMesa && !c.atendidaComanda) {
                            comandaPendiente = true;
                            break;
                        }
                    }

                    if (comandaPendiente) {
                        marcarComoServida(mesa);
                    } else {
                        mostrarAlertaSinPedidos();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comandas>> call, Throwable t) {
            }
        });
    }

    public void mostrarAlertaSinPedidos() {
        panelOscurecer.setVisible(true);
        alertaMesaServida.setVisible(true);
    }

    public void recargarDB() {
        ApiAdapter.getApiService().getMesas().enqueue(new Callback<List<Mesas>>() {
            @Override
            public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
                if (response.isSuccessful()) {
                    listaMesas = response.body();

                    ApiAdapter.getApiService().getComandas().enqueue(new Callback<List<Comandas>>() {
                        @Override
                        public void onResponse(Call<List<Comandas>> call, Response<List<Comandas>> responseComandas) {
                            if (!responseComandas.isSuccessful() || responseComandas.body() == null) return;

                            listaComandas = responseComandas.body();

                            Platform.runLater(() -> {
                                for (Mesas mesa : listaMesas) {
                                    if (mesa.ocupadaMesa) {
                                        boolean pendiente = listaComandas.stream()
                                                .anyMatch(c -> c.numeroMesa == mesa.numeroMesa && !c.atendidaComanda);
                                        if (pendiente) marcarMesaPendiente(mesa);
                                        else marcarMesaOcupada(mesa);
                                    } else {
                                        marcarMesaVacia(mesa);
                                    }
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<Comandas>> call, Throwable t) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Mesas>> call, Throwable throwable) {
            }
        });
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

            ApiAdapter.getApiService().getComandas().enqueue(new Callback<List<Comandas>>() {
                @Override
                public void onResponse(Call<List<Comandas>> call, Response<List<Comandas>> responseComandas) {
                    if (!responseComandas.isSuccessful() || responseComandas.body() == null) return;

                    listaComandas = responseComandas.body();

                    for (Mesas mesa : listaMesas) {
                        if (mesa.ocupadaMesa) {
                            boolean pendiente = false;
                            for (Comandas comanda : listaComandas) {
                                if (comanda.numeroMesa == mesa.numeroMesa && !comanda.atendidaComanda) {
                                    pendiente = true;
                                    break;
                                }
                            }

                            if (pendiente) {
                                marcarMesaPendiente(mesa);
                            } else {
                                marcarMesaOcupada(mesa);
                            }
                        } else {
                            marcarMesaVacia(mesa);
                        }
                    }
                }

                @Override
                    public void onFailure(Call<List<Comandas>> call, Throwable throwable) {
                }
            });

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
