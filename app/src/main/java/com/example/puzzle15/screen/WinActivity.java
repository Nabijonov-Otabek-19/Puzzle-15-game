package com.example.puzzle15.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.example.puzzle15.R;
import com.example.puzzle15.screen.levels.EasyLevelActivity;
import com.example.puzzle15.screen.levels.HardLevelActivity;
import com.example.puzzle15.screen.levels.MediumLevelActivity;

public class WinActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        String time1 = intent.getStringExtra("time");
        String score1 = intent.getStringExtra("score");
        String level1 = intent.getStringExtra("level");

        TextView level2 = findViewById(R.id.txt_level);
        level2.setText(level1);

        TextView time2 = findViewById(R.id.txt_time);
        time2.setText(time1);

        TextView score2 = findViewById(R.id.txt_score);
        score2.setText(score1);

        findViewById(R.id.btn_back2menu).setOnClickListener(view -> {
            startActivity(new Intent(WinActivity.this, MainActivity.class));
        });

        findViewById(R.id.btn_restart).setOnClickListener(view -> {
            if (level1.equals("Easy")) {
                startActivity(new Intent(WinActivity.this, EasyLevelActivity.class));

            } else if (level1.equals("Medium")) {
                startActivity(new Intent(WinActivity.this, MediumLevelActivity.class));

            } else {
                startActivity(new Intent(WinActivity.this, HardLevelActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}