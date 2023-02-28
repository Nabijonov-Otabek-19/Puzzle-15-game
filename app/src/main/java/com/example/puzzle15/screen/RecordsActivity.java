package com.example.puzzle15.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.puzzle15.MyBase;
import com.example.puzzle15.R;

public class RecordsActivity extends AppCompatActivity {

    private TextView easyTime, easyScore;
    private TextView mediumTime, mediumScore;
    private TextView hardTime, hardScore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        loadView();

    }

    @SuppressLint("SetTextI18n")
    private void loadView() {
        MyBase myBase = MyBase.getInstance();

        easyTime = findViewById(R.id.txt_easyTime_record);
        easyScore = findViewById(R.id.txt_easyScore_record);

        mediumTime = findViewById(R.id.txt_mediumTime_record);
        mediumScore = findViewById(R.id.txt_mediumScore_record);

        hardTime = findViewById(R.id.txt_hardTime_record);
        hardScore = findViewById(R.id.txt_hardScore_record);

        easyTime.setText(myBase.getEasyTime());
        easyScore.setText(myBase.getEasyScore() + "");

        mediumTime.setText(myBase.getMediumTime());
        mediumScore.setText(myBase.getMediumScore() + "");

        hardTime.setText(myBase.getHardTime());
        hardScore.setText(myBase.getHardScore() + "");
    }
}