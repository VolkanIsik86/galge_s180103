package com.example.volkangalge.logik;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.volkangalge.MinAdapter;
import com.example.volkangalge.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HighscoreIO {

    public static HighscoreIO highscoreIO;

    private HighscoreIO(){};

    public static HighscoreIO getInstance(){
        if(highscoreIO==null){
            highscoreIO=new HighscoreIO();
        }
        return highscoreIO;
    }

    public void saveScore(Spiller spiller,Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String allescores = prefs.getString("allescore","ingen score");

        Gson gson = new Gson();
        SpillerScores scores;
        if(allescores.equals("ingen score")) {
            scores = new SpillerScores();
        } else {
            scores = gson.fromJson(allescores, SpillerScores.class);
        }
        scores.scores.add(spiller);
        allescores = gson.toJson(scores);
        prefs.edit().putString("allescore",allescores).apply();
    }


    public SpillerScores readScore(Context context){
        SpillerScores scores = null;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String allescores = prefs.getString("allescore","ingen score");
        if(!allescores.equals("ingen score")){
            Gson gson = new Gson();
            scores = gson.fromJson(allescores,SpillerScores.class);
        }
        return scores;
    }

    public void saveName(String name, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("spillernavn", name).apply();
    }

    public String readName(Context context){
        String name = null;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        name = prefs.getString("spillernavn","Noname");
        return name;
    }
}
