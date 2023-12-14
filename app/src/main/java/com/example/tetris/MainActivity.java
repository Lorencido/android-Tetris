package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tetris.models.GameModelInit;
import com.example.tetris.models.GameType;
import com.example.tetris.presenters.GamePresenter;
import com.example.tetris.presenters.GameTurn;
import com.example.tetris.views.GameViewInit;
import com.example.tetris.views.GameViewRunnable;
import com.example.tetris.views.GameWindow;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;

    private GamePresenter gamePresenter;

    private int turnCount = 0;

    private float initialRotationZ = 0;

    private static final float TURN_THRESHOLD = 2f; // Adjust this threshold as needed
    private static final int TURN_NONE = 0;
    private static final int TURN_LEFT = 1;
    private static final int TURN_RIGHT = 2;

    private int turnState = TURN_NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameWindow gameWindow = findViewById(R.id.game_container);
        TextView gameScoreText = findViewById(R.id.game_score);
        TextView gameStatusText = findViewById(R.id.game_status);

        Button gameOptionBtn = findViewById(R.id.game_options_btn);

        Button gameLeaderboardBtn = findViewById(R.id.game_leaderboard_btn);

        gamePresenter = new GamePresenter();
        gamePresenter.setGameModel(GameModelInit.newGameModel(GameType.TETRIS));
        gamePresenter.setGameView(GameViewInit.newGameView(gameWindow, gameScoreText, gameStatusText, gameOptionBtn));

        Button downBtn = findViewById(R.id.up_btn);

        downBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.DOWN));

        gameWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                gamePresenter.turn(GameTurn.FIRE);
            }
        });

        gameOptionBtn.setOnClickListener(v -> gamePresenter.changeStatus());

        gameLeaderboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivity(v);
                gamePresenter.changeStatus();
            }
        });


        gamePresenter.init();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get the gyroscope sensor
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the gyroscope sensor listener
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the gyroscope sensor listener to save battery
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            // Check the rotation around the Z-axis
            float rotationZ = event.values[2];

            //if (turnCount == 0) {
            //    initialRotationZ = rotationZ;
            //}

            //float threshold = 0.5f;

            // Assuming a left turn when rotationZ is negative
            //if (rotationZ < initialRotationZ - threshold && turnCount % 2 == 0) {
                //gamePresenter.turn(GameTurn.RIGHT);
                // Do something when the phone is turned to the left
                //Log.i("test", Float.toString(rotationZ) + "Turned RIGHT");

                //turnCount++;
            //}
            //if (rotationZ > initialRotationZ + threshold && turnCount % 2 == 1) {
                //gamePresenter.turn(GameTurn.LEFT);
                //Log.i("test", Float.toString(rotationZ) + "Turned LEFT");

            //turnCount++;
            //}

            if (Math.abs(rotationZ) > TURN_THRESHOLD) {
                if (rotationZ < 0 && turnState != TURN_LEFT) {
                    Log.i("test", Float.toString(rotationZ) + " Turned RIGHT");
                    gamePresenter.turn(GameTurn.RIGHT);
                    turnState = TURN_LEFT;
                } else if (rotationZ > 0 && turnState != TURN_RIGHT) {
                    Log.i("test", Float.toString(rotationZ) + " Turned LEFT");
                    gamePresenter.turn(GameTurn.LEFT);
                    turnState = TURN_RIGHT;
                }
            } else {
                // No significant turn, reset state
                turnState = TURN_NONE;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something if the accuracy of the sensor changes
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
}