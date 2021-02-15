package com.brithbroadcast.activitiesandintents;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.brithbroadcast.activitiesandintents.ProfileActivity.PROFILE_KEY;

public class MainActivity extends AppCompatActivity {

    private final String BUNDLE_KEY = "SCORE_KEY";
    private int score = 0;
    private TextView scoreBoard;
    private Button clickAwayButton;
    private Button openSecondActivityButton;
    private String playerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG_X", "onCreate: ");


        //instantiating UI
        scoreBoard = findViewById(R.id.main_textview);
        clickAwayButton = findViewById(R.id.click_away_button);
        openSecondActivityButton = findViewById(R.id.second_activity_button);
        //instantiation onClick Listeners /lambda functions
        updateScoreBoard();
        clickAwayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score++;
                updateScoreBoard();
            }
        });

        openSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent -> an action to be performed
                // 2 types of intents ->
                //A) Explicit Intent -> Telling the OS or application what to do
                //B) Implicit Intent -> An action to be performed by the OS

                // Instantiating the intent
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                // Creation of the a bundle if the information
                intent.putExtra(PROFILE_KEY, score);
                // Calling the intent
                startActivityForResult(intent, 707);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG_X", "RequestCode: " + requestCode);
//      if(requestCOde == 707)// Profile Activity
        playerName = data.getStringExtra(PROFILE_KEY);
        scoreBoard.setText(getString(R.string.score_text, playerName, score));
    }


    //String interpolation
    public void updateScoreBoard() {
        scoreBoard.setText(getString(R.string.score_text, playerName, score));
    }

    //Data persistence method that goes together with
    //android:configChanges="orientation|screenSize"> - android manifest
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("TAG_X", "Orientation" + newConfig.orientation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG_X", "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG_X", "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG_X", "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG_X", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG_X", "onStop: ");
    }

    //checking for saved previous state
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt(BUNDLE_KEY);
        updateScoreBoard();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY, score);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG_X", "onDestroy: ");
    }
}