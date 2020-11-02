package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.volkangalge.logik.HighscoreIO;
import com.example.volkangalge.logik.SpillerScores;
import com.google.gson.Gson;

public class Highscore extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        HighscoreIO highscoreIO = HighscoreIO.getInstance();
        SpillerScores spillerScores = highscoreIO.readScore(this);
        if(spillerScores!=null){
            listView=findViewById(R.id.scorelist);
            MinAdapter minAdapter = new MinAdapter(this,spillerScores);
            listView.setAdapter(minAdapter);
        }
    }
    public void onBackPressed(){
        Intent start = new Intent(this,MainActivity.class);
        this.finish();
        startActivity(start);
    }
}