package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start, hjælp, afslut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//3 knapper initaliseres til hovedmenu. Spillet starter fra start knappen.
        start = findViewById(R.id.start);
        hjælp = findViewById(R.id.hjælp);
        afslut = findViewById(R.id.afslut);

        start.setOnClickListener(this);
        hjælp.setOnClickListener(this);
        afslut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //starter gameActivity som er selve spillet
        if(view==start){
            Intent start = new Intent(this,GameActivity.class);
            startActivity(start);
            finish();
        //Afslutter applicationnen
        } else if (view == afslut){
            finish();
            System.exit(0);
        //Starter help aktiviteten som er en kort instruktion for spillet
        }else if(view == hjælp){
            Intent help = new Intent(this,Help.class);
            startActivity(help);
            finish();
        }

    }
}