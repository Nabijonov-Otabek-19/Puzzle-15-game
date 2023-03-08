package uz.gita.puzzle15;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MyBase.initialize(this);
        Music.initialize(this);
    }
}
