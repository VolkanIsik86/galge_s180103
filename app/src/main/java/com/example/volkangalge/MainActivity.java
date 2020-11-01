package com.example.volkangalge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Spiller:");
            EditText et = new EditText(this);
            et.setText("");
            dialog.setView(et);
            dialog.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent start = new Intent(MainActivity.this,GameActivity.class);
                    start.putExtra("spillernavn",et.getText().toString());
                    startActivity(start);
                    finish();
                }
            });
            dialog.setNegativeButton("Annullere", null);
            dialog.show();





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