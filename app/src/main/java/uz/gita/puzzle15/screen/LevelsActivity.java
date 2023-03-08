package uz.gita.puzzle15.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.puzzle15.R;
import uz.gita.puzzle15.screen.levels.EasyLevelActivity;
import uz.gita.puzzle15.screen.levels.HardLevelActivity;
import uz.gita.puzzle15.screen.levels.MediumLevelActivity;

public class LevelsActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        findViewById(R.id.btn_easy).setOnClickListener(view ->
                startActivity(new Intent(LevelsActivity.this, EasyLevelActivity.class)));

        findViewById(R.id.btn_medium).setOnClickListener(view ->
                startActivity(new Intent(LevelsActivity.this, MediumLevelActivity.class)));

        findViewById(R.id.btn_hard).setOnClickListener(view ->
                startActivity(new Intent(LevelsActivity.this, HardLevelActivity.class)));

        findViewById(R.id.btn_back).setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            onBackPressed();
        });
    }
}