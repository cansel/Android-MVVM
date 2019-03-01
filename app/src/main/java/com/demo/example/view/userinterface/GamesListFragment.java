package com.demo.example.view.userinterface;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.example.networkservice.model.GamesData;
import com.demo.example.view.adapter.GamesListAdapter;
import com.demo.example.viewmodel.GamesListModel;
import com.mvvm.restapi.example.R;
import com.mvvm.restapi.example.databinding.GamesListBinding;

import java.util.List;


public class GamesListFragment extends Fragment {

    public static final String TAG = "GamesListFragment";
    private GamesListBinding mGamesListBinding;
    private GamesListAdapter mGamesListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        mGamesListBinding = DataBindingUtil.inflate(inflater, R.layout.games_list,
                container, false);
        mGamesListAdapter = new GamesListAdapter();
        mGamesListBinding.gameList.setAdapter(mGamesListAdapter);
        mGamesListBinding.setIsDataLoad(true);
        return mGamesListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final GamesListModel viewModel =
                ViewModelProviders.of(this).get(GamesListModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(GamesListModel viewModel) {
        if (viewModel.getGamesListObservable() == null) {
            Log.e(TAG, "game data is null");
            mGamesListBinding.loadGame.setText(R.string.error);
            return;
        }
        viewModel.getGamesListObservable().observe(this, new Observer<List<GamesData>>() {
            @Override
            public void onChanged(List<GamesData> gameData) {
                if (gameData != null) {
                    mGamesListBinding.setIsDataLoad(false);
                    mGamesListAdapter.setGamesData(gameData);
                }
            }
        });
    }
}
