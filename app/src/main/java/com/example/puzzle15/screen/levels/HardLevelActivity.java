package com.example.puzzle15.screen.levels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puzzle15.Coordinate;
import com.example.puzzle15.Music;
import com.example.puzzle15.MyBase;
import com.example.puzzle15.R;
import com.example.puzzle15.screen.WinActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HardLevelActivity extends AppCompatActivity {

    private TextView textScore;
    private Chronometer textTime;
    private Button[][] items;
    private List<Integer> numbers;
    private Coordinate emptySpace;
    private Music music;
    private MyBase myBase;

    private final String SCORE = "score";
    private final String TIME = "time";
    private final String DATA = "data";

    private String[] datas;
    private int score = 0;
    private long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_level);

        loadView();
        loadData();
        dataToView();
        textTime.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        textScore.setText(String.valueOf(score));
        textTime.setBase(SystemClock.elapsedRealtime() - time);

        if (datas != null) {
            int index = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (datas[index].equals("0")) {
                        emptySpace.setX(j);
                        emptySpace.setY(i);
                        items[i][j].setText("");
                        items[i][j].setBackgroundResource(R.color.color_item_empty);
                    } else {
                        items[i][j].setText(datas[index]);
                        items[i][j].setBackgroundResource(R.color.color_item);
                    }
                    index++;
                }
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt(SCORE, 0);
        time = savedInstanceState.getLong(TIME, SystemClock.elapsedRealtime());
        String text = savedInstanceState.getString(DATA, "");
        datas = text.split("#");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(DATA, getNumbers());
        outState.putInt(SCORE, score);
        outState.putLong(TIME, SystemClock.elapsedRealtime() - textTime.getBase());
        super.onSaveInstanceState(outState);
    }

    private String getNumbers() {
        StringBuilder str = new StringBuilder();
        for (Button[] item : items) {
            for (Button button : item) {
                if (button.getText().toString().isEmpty())
                    str.append(0).append("#");
                else
                    str.append(button.getText().toString()).append("#");
            }
        }
        return str.toString();
    }

    private void loadView() {
        textScore = findViewById(R.id.text_score);
        textTime = findViewById(R.id.text_time);

        music = Music.getInstance();
        myBase = MyBase.getInstance();

        findViewById(R.id.btn_finish).setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setTitle("Exit").setMessage("Do you want to finish game ?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        HardLevelActivity.this.onBackPressed();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.cancel();
                    }).create().show();
        });

        findViewById(R.id.btn_restart).setOnClickListener(v -> restart());

        final ViewGroup group = findViewById(R.id.container);
        final int count = group.getChildCount();
        items = new Button[5][5];

        for (int i = 0; i < count; i++) {
            final View view = group.getChildAt(i);
            final Button button = (Button) view;
            final int y = i / 5;
            final int x = i % 5;
            button.setOnClickListener(v -> onItemClick(button, x, y));
            items[y][x] = button;
        }
        emptySpace = new Coordinate(4, 4);
    }

    private void loadData() {
        numbers = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            numbers.add(i);
        }
    }

    private void dataToView() {

        Collections.shuffle(numbers);
        items[emptySpace.getY()][emptySpace.getX()].setBackgroundResource(R.color.color_item);
        emptySpace.setX(4);
        emptySpace.setY(4);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                final int index = 5 * i + j;
                if (index < 24) {
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
        if (myBase.getSound()) music.makeSound();
        else music.stopSound();

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
        if (emptySpace.getX() == 4 && emptySpace.getY() == 4) {
            for (int i = 0; i < 23; i++) {
                if (items[i / 5][i % 5].getText().toString().equals(String.valueOf(i + 1))) {
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
            Intent intent = new Intent(HardLevelActivity.this, WinActivity.class);
            intent.putExtra("score", textScore.getText());
            intent.putExtra("time", textTime.getText());
            intent.putExtra("level", "Hard");
            startActivity(intent);

            setDataToRecords();
            restart();
        }
    }

    private void setDataToRecords() {
        int dbScore = myBase.getHardScore();
        int currScore = Integer.parseInt(textScore.getText().toString());

        if (dbScore != 0 && dbScore < currScore) {
            myBase.setHardScore(Integer.parseInt(textScore.getText().toString()));
            myBase.setHardTime(textTime.getText().toString());
        } else if (dbScore == 0) {
            myBase.setHardScore(Integer.parseInt(textScore.getText().toString()));
            myBase.setHardTime(textTime.getText().toString());
        }
    }
}