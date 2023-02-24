package com.example.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MediumLevelActivity extends AppCompatActivity {

    private TextView textScore;
    private Chronometer textTime;
    private Button[][] items;
    private List<Integer> numbers;
    private Coordinate emptySpace;
    private int score;
    private MediaPlayer clickAudio;
    private ViewGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_level);

        loadView();
        loadData();
        dataToView();
        textTime.start();
    }

    private void loadView() {
        textScore = findViewById(R.id.text_score);
        textTime = findViewById(R.id.text_time);

        clickAudio = MediaPlayer.create(this, R.raw.gate_click);

        findViewById(R.id.btn_finish).setOnClickListener(view -> MediumLevelActivity.this.finish());
        findViewById(R.id.btn_restart).setOnClickListener(v -> restart());

        group = findViewById(R.id.container);
        final int count = group.getChildCount();
        items = new Button[4][4];

        for (int i = 0; i < count; i++) {
            final View view = group.getChildAt(i);
            final Button button = (Button) view;

            final int y = i / 4;
            final int x = i % 4;

            button.setOnClickListener(v -> onItemClick(button, x, y));
            items[y][x] = button;
        }
        emptySpace = new Coordinate(3, 3);
    }

    private void loadData() {
        numbers = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        }
    }

    private void dataToView() {
        score = 0;
        textScore.setText("0");

        Collections.shuffle(numbers);

        items[emptySpace.getY()][emptySpace.getX()].setBackgroundResource(R.color.color_item);
        emptySpace.setX(3);
        emptySpace.setY(3);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final int index = 4 * i + j;
                if (index < 15) {
                    int number = numbers.get(index);
                    items[i][j].setText(String.valueOf(number));
                } else {
                    items[i][j].setText("");
                    items[i][j].setBackgroundResource(R.color.color_item_empty);
                }
            }
        }
    }

    private void restart() {
        dataToView();
        textTime.setBase(SystemClock.elapsedRealtime());
        textTime.start();
    }

    private void onItemClick(Button button, int x, int y) {

        clickAudio.start();

        final int dx = Math.abs(emptySpace.getX() - x);
        final int dy = Math.abs(emptySpace.getY() - y);

        if (dx + dy == 1) {
            textScore.setText(String.valueOf(++score));

            final String text = button.getText().toString();
            button.setText("");
            button.setBackgroundResource(R.color.color_item_empty);

            final Button temp = items[emptySpace.getY()][emptySpace.getX()];
            temp.setText(text);
            temp.setBackgroundResource(R.color.color_item);

            emptySpace.setX(x);
            emptySpace.setY(y);

            checkWin();
        }
    }

    private void checkWin() {
        boolean isWin = false;
        if (emptySpace.getX() == 3 && emptySpace.getY() == 3) {
            for (int i = 0; i < 14; i++) {
                if (items[i / 4][i % 4].getText().toString().equals(String.valueOf(i + 1))) {
                    isWin = true;
                } else {
                    isWin = false;
                    break;
                }
            }
        }
        if (isWin) {
            textTime.stop();
            Toast.makeText(this, "You win", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MediumLevelActivity.this, WinActivity.class);
            intent.putExtra("score", textScore.getText());
            intent.putExtra("time", textTime.getText());
            intent.putExtra("level", "Medium");
            startActivity(intent);

            MyBase myBase = new MyBase(this);
            myBase.setLevel("Easy");
            myBase.setScore(textScore.getText().toString());
            myBase.setTime(textTime.getText().toString());
            restart();
        }
    }
}