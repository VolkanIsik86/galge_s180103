package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.volkangalge.logik.SpillerScores;
import com.google.gson.Gson;

public class Highscore extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String allescores = prefs.getString("allescore","{\"scores\":[{\"navn\":\"noname\",\"score\":0}]}");
        Gson gson = new Gson();
        SpillerScores scores = gson.fromJson(allescores,SpillerScores.class);
        listView=findViewById(R.id.scorelist);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.scores, R.id.spillernavn, scores.scores.);


    }
}