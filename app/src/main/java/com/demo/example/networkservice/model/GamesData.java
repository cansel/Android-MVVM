package com.demo.example.networkservice.model;

import com.google.gson.annotations.SerializedName;

public class GamesData {

    @SerializedName("gameName")
    public String mGameName;
    @SerializedName("gameId")
    private
    String mGameId;
    @SerializedName("imageUrl")
    public String mImageUrl;

    public String getGameId() {
        return mGameId;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
