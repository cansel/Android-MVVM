package com.demo.example.networkservice;

import com.demo.example.networkservice.model.GameModel;

import retrofit2.Call;
import retrofit2.http.GET;

interface NetworkInterface {

    String API_HOST_URL = "https://api.jsonbin.io/";

    @GET("b/5c790f0d2e4731596f120ab0")
    Call<GameModel> getGamesDataList();

}
