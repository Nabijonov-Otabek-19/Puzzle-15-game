package uz.gita.puzzle15.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.puzzle15.R;
import uz.gita.puzzle15.screen.levels.EasyLevelActivity;
import uz.gita.puzzle15.screen.levels.HardLevelActivity;
import uz.gita.puzzle15.screen.levels.MediumLevelActivity;

public class WinActivity extends AppCompatActivity {

    private TextView level2, time2, score2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        String time1 = intent.getStringExtra("time");
        String score1 = intent.getStringExtra("score");
        String level1 = intent.getStringExtra("level");

        level2 = findViewById(R.id.txt_level);
        level2.setText(level1);

        time2 = findViewById(R.id.txt_time);
        time2.setText(time1);

        score2 = findViewById(R.id.txt_score);
        score2.setText(score1);

        findViewById(R.id.btn_back2menu).setOnClickListener(view -> {
            startActivity(new Intent(WinActivity.this, MainActivity.class));
            onBackPressed();
        });

        findViewById(R.id.btn_restart).setOnClickListener(view -> {
            if (level1.equals("Easy")) {
                startActivity(new Intent(WinActivity.this, EasyLevelActivity.class));

            } else if (level1.equals("Medium")) {
                startActivity(new Intent(WinActivity.this, MediumLevelActivity.class));

            } else {
                startActivity(new Intent(WinActivity.this, HardLevelActivity.class));
            }
            onBackPressed();
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        level2.setText(savedInstanceState.getString("level", ""));
        time2.setText(savedInstanceState.getString("time", ""));
        score2.setText(savedInstanceState.getString("time", ""));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("score", score2.getText().toString());
        outState.putString("time", time2.getText().toString());
        outState.putString("level", level2.getText().toString());
    }
}