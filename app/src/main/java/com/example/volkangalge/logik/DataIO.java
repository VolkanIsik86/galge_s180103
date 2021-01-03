package com.example.volkangalge.logik;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Klassen bruges til at kommuniker med preference manager
 */
public class DataIO {

    public static DataIO dataIO;

    private DataIO(){};

    public static DataIO getInstance(){
        if(dataIO ==null){
            dataIO =new DataIO();
        }
        return dataIO;
    }

    /**
     * Gemmer spillerens score i localt lager
     * @param spiller den nuværende spiller
     * @param context aktivetet som man befinder sig i
     */
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

    /**
     * Læser spiller score som er gemt i localt lager
     * @param context den aktivitet som man befinder sig i
     * @return
     */
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

    /**
     * Gemmer spiller navnet for at at bruge det i forbindelse med at gemme spiller score
     * @param name Spiller navnet
     * @param context den aktivitet som man befinder sig i
     */
    public void saveName(String name, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("spillernavn", name).apply();
    }

    /**
     * Læser navner på den nuverænde spiller som gemt i localt lager
     * @param context den aktivitet som man befinder sig i
     * @return Spiller navnet
     */
    public String readName(Context context){
        String name = null;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        name = prefs.getString("spillernavn","Noname");
        return name;
    }

    /**
     * Gemmer den ønskede spille mode
     * @param difficulty den spille mode man har valgt
     * @param context den aktivitet som man befinder sig i
     */
    public void saveDifficulty(String difficulty, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("difficulty", difficulty).apply();
    }

    /**
     * Læser den spille mode man har valgt
     * @param context den aktivitet som man befinder sig i
     * @return Spille mode
     */
    public String readDifficulty(Context context){
        String difficulty = null;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        difficulty = prefs.getString("difficulty","Nodifficulty");
        return difficulty;
    }

}
