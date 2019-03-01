package com.demo.example.networkservice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.demo.example.networkservice.model.GameModel;
import com.demo.example.networkservice.model.GamesData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetGamesAPI {

    private final NetworkInterface mNetworkInterface;
    private static GetGamesAPI mGamesAPI;

    private GetGamesAPI() {
        mNetworkInterface = new OkHttpInit().getHttpClient().create(NetworkInterface.class);
    }

    public synchronized static GetGamesAPI getInstance() {
        if (mGamesAPI == null) {
            mGamesAPI = new GetGamesAPI();
        }
        return mGamesAPI;
    }

    @Nullable
    public LiveData<List<GamesData>> getGamesList() {
        final MutableLiveData<List<GamesData>> gamesData = new MutableLiveData<>();

        mNetworkInterface.getGamesDataList().enqueue(new Callback<GameModel>() {
            @Override
            public void onResponse(@NonNull Call<GameModel> call,
                                   @NonNull Response<GameModel> response) {
                GameModel body = response.body();
                if (body == null || body.getGamesData() == null) {
                    Log.e("failure in response", "something wrong in response");
                    gamesData.setValue(null);
                    return;
                }

                List<GamesData> data = new ArrayList<>(body.getGamesData().values());
                gamesData.setValue(data);
            }

            @Override
            public void onFailure(@NonNull Call<GameModel> call,
                                  @NonNull Throwable t) {
                Log.e("failure in response", "" + call.request());
                gamesData.setValue(null);
            }
        });

        return gamesData;
    }

}
