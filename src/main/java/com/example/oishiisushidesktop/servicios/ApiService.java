package com.example.oishiisushidesktop.servicios;

import java.util.List;

import com.example.oishiisushidesktop.entidades.Comandas;
import com.example.oishiisushidesktop.entidades.Mesas;
import com.example.oishiisushidesktop.entidades.Platos;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @GET("platos")
    Call<List<Platos>> getPlatos();

    @GET("comandas")
    Call<List<Comandas>> getComandas();

    @GET("mesas")
    Call<List<Mesas>> getMesas();

    @PUT("mesas/{numeroMesa}")
    Call<Mesas> updateMesas(@Path("numeroMesa") int numeroMesa, @Body Mesas mesa);

    @POST("comandas")
    Call<Comandas> createComanda(@Body Comandas comanda);

}