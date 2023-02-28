package com.example.puzzle15;

import android.content.Context;
import android.content.SharedPreferences;

public class MyBase {

    private static MyBase instance;

    public static final String SHARED_PREF = "sharedPref";
    public static final String SOUND = "sound";

    public static final String EASY_TIME = "easyTime";
    public static final String EASY_SCORE = "easyScore";

    public static final String MEDIUM_TIME = "mediumTime";
    public static final String MEDIUM_SCORE = "mediumScore";

    public static final String HARD_TIME = "hardTime";
    public static final String HARD_SCORE = "hardScore";


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private MyBase(Context context) {
        pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static MyBase getInstance() {
        return instance;
    }

    public static void initialize(Context context) {
        if (instance == null) instance = new MyBase(context);
    }


    public void setEasyTime(String time) {
        editor.putString(EASY_TIME, time).apply();
    }

    public String getEasyTime() {
        return pref.getString(EASY_TIME, "00:00");
    }

    public void setEasyScore(int score) {
        editor.putInt(EASY_SCORE, score).apply();
    }

    public int getEasyScore() {
        return pref.getInt(EASY_SCORE, 0);
    }

    public void setMediumTime(String time) {
        editor.putString(MEDIUM_TIME, time).apply();
    }

    public String getMediumTime() {
        return pref.getString(MEDIUM_TIME, "00:00");
    }

    public void setMediumScore(int score) {
        editor.putInt(MEDIUM_SCORE, score).apply();
    }

    public int getMediumScore() {
        return pref.getInt(MEDIUM_SCORE, 0);
    }

    public void setHardTime(String time) {
        editor.putString(HARD_TIME, time).apply();
    }

    public String getHardTime() {
        return pref.getString(HARD_TIME, "00:00");
    }

    public void setHardScore(int time) {
        editor.putInt(HARD_SCORE, time).apply();
    }

    public int getHardScore() {
        return pref.getInt(HARD_SCORE, 0);
    }

    public void setSound(boolean is) {
        editor.putBoolean(SOUND, is).apply();
    }

    public boolean getSound() {
        return pref.getBoolean(SOUND, true);
    }
}
