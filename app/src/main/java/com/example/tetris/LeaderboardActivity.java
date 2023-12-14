package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tetris.presenters.ScoreBoardPresenter;
import com.example.tetris.presenters.ScoreboardModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private List<ScoreboardModel> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        /*
        scores = new ArrayList<>();
        scores.add(new ScoreboardModel("score", 100));
        scores.add(new ScoreboardModel("score", 75));
        scores.add(new ScoreboardModel("score", 50));
        */

        loadScoresFromSharedPreferences();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewScores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ScoreBoardPresenter adapter = new ScoreBoardPresenter(scores);
        recyclerView.setAdapter(adapter);
    }

    public void saveScoresToSharedPreferences(List<ScoreboardModel> leaderboardScores) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        if(leaderboardScores.isEmpty()) {
            scores = new ArrayList<>();
            scores.add(new ScoreboardModel("player 1", 0));
            String scoresJson = gson.toJson(scores);
            editor.putString("scores", scoresJson);
            editor.apply();
        } else {
            String scoresJson = gson.toJson(leaderboardScores);
            editor.putString("scores", scoresJson);
            editor.apply();
        }
    }

    private void loadScoresFromSharedPreferences() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String scoresJson = preferences.getString("scores", null);
        if (scoresJson != null) {
            Type listType = new TypeToken<ArrayList<ScoreboardModel>>() {}.getType();
            scores = gson.fromJson(scoresJson, listType);
            Collections.sort(scores, (score1, score2) -> Integer.compare(score2.getScore(), score1.getScore()));
        } else {
            scores = new ArrayList<>();
            scores.add(new ScoreboardModel("player 1", 0));
            scores.add(new ScoreboardModel("player 2", 0));
            scores.add(new ScoreboardModel("player 3", 0));
        }
    }
}