package com.demo.example.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.demo.example.networkservice.GetGamesAPI;
import com.demo.example.networkservice.model.GamesData;

import java.util.List;

public class GamesListModel extends ViewModel {

    @Nullable
    public LiveData<List<GamesData>> getGamesListObservable() {
        return GetGamesAPI.getInstance().getGamesList();
    }
}