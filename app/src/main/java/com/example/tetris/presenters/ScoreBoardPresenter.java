package com.example.tetris.presenters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tetris.R;

import java.util.List;

public class ScoreBoardPresenter extends RecyclerView.Adapter<ScoreBoardPresenter.ViewHolder> {
    private List<ScoreboardModel> scores;

    public ScoreBoardPresenter(List<ScoreboardModel> scores) {
        this.scores = scores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scoreboard_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScoreboardModel score = scores.get(position);
        holder.textViewPlayerName.setText(score.getPlayerName());
        holder.textViewScore.setText("Score: " + score.getScore());
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlayerName;
        TextView textViewScore;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPlayerName = itemView.findViewById(R.id.textViewPlayerName);
            textViewScore = itemView.findViewById(R.id.textViewScore);
        }
    }
}

