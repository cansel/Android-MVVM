package com.demo.example.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import com.demo.example.networkservice.GetGamesAPI;
import com.demo.example.networkservice.model.GamesData;

import java.util.List;

public class GamesListModel extends AndroidViewModel {

    private final LiveData<List<GamesData>> gamesListObservable;

    public GamesListModel(Application application) {
        super(application);
        gamesListObservable = GetGamesAPI.getInstance().getGamesList();
    }

    @Nullable
    public LiveData<List<GamesData>> getGamesListObservable() {
        return gamesListObservable;
    }
}
