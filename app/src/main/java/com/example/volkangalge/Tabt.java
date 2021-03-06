package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.volkangalge.logik.DataIO;

/**
 * Tabt spiller skærm
 */
public class Tabt extends AppCompatActivity implements View.OnClickListener {
    TextView antalforsøg;
    Button ja,nej;
    MediaPlayer tabt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt);
        Intent intent = getIntent();
        tabt = MediaPlayer.create(this,R.raw.you_lose);
        tabt.setVolume(1,1);
        tabt.start();
        antalforsøg=findViewById(R.id.ordetvar);
        antalforsøg.setText(intent.getStringExtra("ordet"));
        ja = findViewById(R.id.tabtja);
        ja.setOnClickListener(this);
        nej=findViewById(R.id.tabtnej);
        nej.setOnClickListener(this);
    }

    /**
     * Man kan vælge om man vil spille videre eller ej
     * @param v knappen der blev trykket
     */
    @Override
    public void onClick(View v) {
        if(v==ja){
            if(DataIO.getInstance().readDifficulty(this).equals("valgord")){
                Intent start = new Intent(this,ValgOrdActivity.class);
                startActivity(start);
                finish();
            }else {
                Intent start = new Intent(this,GameActivity.class);
                startActivity(start);
                finish();
            }
        }
        if(v==nej){
            Intent hovedmenu = new Intent(this,MainActivity.class);
            startActivity(hovedmenu);
            finish();
        }
    }
    public void onBackPressed(){
        Intent start = new Intent(this,MainActivity.class);
        this.finish();
        startActivity(start);
    }
    @Override
    public void onDestroy() {
        tabt.stop();
        tabt.release();
        super.onDestroy();
    }
}