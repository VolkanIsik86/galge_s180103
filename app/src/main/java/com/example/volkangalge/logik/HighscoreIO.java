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

    public void saveScore(SpillerScores spillerScores){}


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
}
