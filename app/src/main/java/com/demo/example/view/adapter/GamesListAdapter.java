package com.demo.example.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.example.networkservice.model.GamesData;
import com.mvvm.restapi.example.R;
import com.mvvm.restapi.example.databinding.GameItemBinding;

import java.util.List;
import java.util.Objects;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesListViewHolder> {

    private List<GamesData> mGamesData;

    public void setGamesData(final List<GamesData> gamesData) {
        if (mGamesData == null) {
            mGamesData = gamesData;
            notifyItemRangeInserted(0, mGamesData.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mGamesData.size();
                }

                @Override
                public int getNewListSize() {
                    return gamesData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mGamesData.get(oldItemPosition).getGameId().equals
                            (gamesData.get(newItemPosition).getGameId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    GamesData newGamesData = gamesData.get(newItemPosition);
                    GamesData oldGamesData = gamesData.get(oldItemPosition);
                    return newGamesData.getGameId().equals(oldGamesData.getGameId())
                            && Objects.equals(newGamesData.getImageUrl(), oldGamesData.getImageUrl());
                }
            });
            mGamesData = gamesData;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public GamesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GameItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.game_item,
                        parent, false);

        return new GamesListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesListViewHolder holder, int position) {
        holder.binding.setGamesData(mGamesData.get(position));
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return mGamesData == null ? 0 : mGamesData.size();
    }

    static class GamesListViewHolder extends RecyclerView.ViewHolder {

        final GameItemBinding binding;

        GamesListViewHolder(GameItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
