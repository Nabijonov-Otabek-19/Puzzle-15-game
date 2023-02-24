package com.example.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        findViewById(R.id.btn_back).setOnClickListener(view -> finish());
    }
}