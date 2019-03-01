package com.demo.example.networkservice.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Map;


public class GameModel {

    @SerializedName("games")
    private Map<String, GamesData> mGamesData;

    @Nullable
    public Map<String, GamesData> getGamesData() {
        return mGamesData;
    }
}
