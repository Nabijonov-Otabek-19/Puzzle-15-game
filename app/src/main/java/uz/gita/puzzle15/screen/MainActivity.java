package uz.gita.puzzle15.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import uz.gita.puzzle15.MyBase;

import com.example.puzzle15.R;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyBase myBase = MyBase.getInstance();
        ImageView sound = findViewById(R.id.btn_sound);

        if (myBase.getSound()) {
            sound.setImageResource(R.drawable.ic_sound);

        } else sound.setImageResource(R.drawable.ic_mute);

        sound.setOnClickListener(view -> {
            if (myBase.getSound()) {
                sound.setImageResource(R.drawable.ic_mute);
                myBase.setSound(false);
            } else {
                sound.setImageResource(R.drawable.ic_sound);
                myBase.setSound(true);
            }
        });

        findViewById(R.id.btn_levels).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LevelsActivity.class));
        });

        findViewById(R.id.btn_quit).setOnClickListener(view -> {
            finishAffinity();
        });

        findViewById(R.id.btn_about).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, AboutActivity.class)));

        findViewById(R.id.btn_records).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, RecordsActivity.class)));

    }
}