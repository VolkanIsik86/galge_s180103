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

        start = findViewById(R.id.start);
        hjælp = findViewById(R.id.hjælp);
        afslut = findViewById(R.id.afslut);

        start.setOnClickListener(this);
        hjælp.setOnClickListener(this);
        afslut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view==start){
            Intent start = new Intent(this,GameActivity.class);
            startActivity(start);

        } else if (view == afslut){
            finish();
            System.exit(0);
        }

    }
}