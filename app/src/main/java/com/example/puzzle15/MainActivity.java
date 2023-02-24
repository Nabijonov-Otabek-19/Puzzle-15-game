package com.example.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_levels).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LevelsActivity.class)));

        findViewById(R.id.btn_quit).setOnClickListener(view -> MainActivity.this.finishAffinity());

        findViewById(R.id.btn_about).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, AboutActivity.class)));

        findViewById(R.id.btn_records).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, RecordsActivity.class)));

    }
}