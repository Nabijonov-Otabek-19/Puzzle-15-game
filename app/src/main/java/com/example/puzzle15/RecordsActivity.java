package com.example.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class RecordsActivity extends AppCompatActivity {

    TextView level, time, score;
    private MyBase myBase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        level = findViewById(R.id.txt_level_record);
        time = findViewById(R.id.txt_time_record);
        score = findViewById(R.id.txt_score_record);

        myBase = new MyBase(this);

        level.setText(myBase.getLevel());
        time.setText(myBase.getTime());
        score.setText(myBase.getScore());
    }
}