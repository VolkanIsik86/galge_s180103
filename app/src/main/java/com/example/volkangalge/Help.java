package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity implements View.OnClickListener {
    Button returnknappen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        //retur knappen initaliseres som returnerer til hoved menu
        returnknappen = findViewById(R.id.returnknappen);
        returnknappen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == returnknappen) {
            Intent menu = new Intent(this, MainActivity.class);
            startActivity(menu);
            finish();
        }
    }
}