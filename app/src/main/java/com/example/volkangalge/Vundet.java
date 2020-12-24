package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Vundet extends AppCompatActivity implements View.OnClickListener {
    TextView antalforsøg;
    Button ja,nej;
    MediaPlayer vundet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);
        Intent intent = getIntent();
        vundet = MediaPlayer.create(this,R.raw.you_win);
        vundet.setVolume(1,1);
        vundet.start();
        antalforsøg=findViewById(R.id.forsøg);
        antalforsøg.setText(intent.getStringExtra("antalforsøg"));
        ja = findViewById(R.id.javundet);
        ja.setOnClickListener(this);
        nej=findViewById(R.id.nejvundet);
        nej.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==ja){
            Intent start = new Intent(this,GameActivity.class);
            startActivity(start);
            finish();
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
        vundet.stop();
        vundet.release();
        super.onDestroy();
    }
}