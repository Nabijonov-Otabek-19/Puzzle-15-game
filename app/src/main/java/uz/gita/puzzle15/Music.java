package uz.gita.puzzle15;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.puzzle15.R;

public class Music {

    private static Music music;
    private final MediaPlayer clickAudio;

    private Music(Context context) {
        clickAudio = MediaPlayer.create(context, R.raw.gate_click);
    }

    public static Music getInstance() {
        return music;
    }

    public static void initialize(Context context) {
        if (music == null) music = new Music(context);

    }

    public void makeSound() {
        clickAudio.start();
    }

    public void stopSound() {
        clickAudio.stop();
    }
}
