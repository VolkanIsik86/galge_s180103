package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Vundet extends AppCompatActivity implements View.OnClickListener {
    TextView antalforsøg;
    Button ja,nej;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);
        Intent intent = getIntent();
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
}