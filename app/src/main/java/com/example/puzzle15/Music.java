package com.example.puzzle15;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {

    private final MediaPlayer clickAudio;

    public Music(Context context) {
        clickAudio = MediaPlayer.create(context, R.raw.gate_click);
    }

    public void makeSound() {
        clickAudio.start();
    }

    public void stopSound() {
        clickAudio.stop();
    }
}
