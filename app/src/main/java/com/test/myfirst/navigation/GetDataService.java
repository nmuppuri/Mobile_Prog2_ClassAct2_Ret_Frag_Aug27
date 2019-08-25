package com.test.myfirst.navigation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("Vy2abloQD")
    Call<List<Pokemon>> getPokemons();

    @GET("E14trR2lD")
    Call<PokemonPojo> getPokemonObj();
}
