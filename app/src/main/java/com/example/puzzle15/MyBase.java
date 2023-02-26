package com.example.puzzle15;

import android.content.Context;
import android.content.SharedPreferences;

public class MyBase {

    public static final String SHARED_PREF = "sharedPref";
    public static final String LEVEL = "level";
    public static final String TIME = "time";
    public static final String SCORE = "score";
    public static final String SOUND = "sound";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public MyBase(Context context) {
        pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLevel(String level) {
        editor.putString(LEVEL, level).apply();
    }

    public String getLevel() {
        return pref.getString(LEVEL, "...");
    }

    public void setTime(String time) {
        editor.putString(TIME, time).apply();
    }

    public String getTime() {
        return pref.getString(TIME, "00:00");
    }

    public void setScore(String score) {
        editor.putString(SCORE, score).apply();
    }

    public String getScore() {
        return pref.getString(SCORE, "0");
    }

    public void setSound(boolean is) {
        editor.putBoolean(SOUND, is).apply();
    }

    public boolean getSound() {
        return pref.getBoolean(SOUND, true);
    }
}
