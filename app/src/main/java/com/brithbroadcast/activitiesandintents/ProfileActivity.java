package com.brithbroadcast.activitiesandintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    public static final String PROFILE_KEY = "profile_key";
    private EditText playNameEditText;
    private TextView scoreTextView;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        playNameEditText = findViewById(R.id.player_name_edittext);
        scoreTextView = findViewById(R.id.score_textview);
        finishButton = findViewById(R.id.finish_button);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Passing back the data to previous caller intent
                Intent resultIntent = new Intent();
                String name = playNameEditText.getText().toString().trim();
                StringBuilder s = new StringBuilder();
                resultIntent.putExtra(PROFILE_KEY, s.append(name).append("'s").toString());
                setResult(707, resultIntent);
                finish(); // OnDestroy will be called without the OnStop - onStop is skipped

            }
        });

        // Extracting date coming from intent bundle
        // Note: the -1 value serves as test to make sure that the bundle/data crossover is working properly
        int currentScore = getIntent().getIntExtra(PROFILE_KEY, -1);
        scoreTextView.setText(getString(R.string.score_text, "", currentScore));


    }
}