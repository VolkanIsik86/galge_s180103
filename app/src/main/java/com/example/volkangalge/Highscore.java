package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.volkangalge.logik.DataIO;
import com.example.volkangalge.logik.SpillerScores;

public class Highscore extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_highscore);

        SpillerScores spillerScores = DataIO.getInstance().readScore(this);
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