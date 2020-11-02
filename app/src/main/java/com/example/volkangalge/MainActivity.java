package com.example.volkangalge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.example.volkangalge.logik.DataIO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    Button start, hjælp, afslut,highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//3 knapper initaliseres til hovedmenu. Spillet starter fra start knappen.
        start = findViewById(R.id.start);
        hjælp = findViewById(R.id.hjælp);
        afslut = findViewById(R.id.afslut);
        highscore = findViewById(R.id.Highgamescores);


        start.setOnClickListener(this);
        hjælp.setOnClickListener(this);
        afslut.setOnClickListener(this);
        highscore.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        //starter gameActivity som er selve spillet
        if(view==start){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this,R.style.CustomDialogTheme);
            dialog.setTitle("Spiller:");
            EditText et = new EditText(this);
            et.setTextColor(Color.parseColor("#CCFFFFFF"));
            et.setText("");
            dialog.setView(et);
            dialog.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    DataIO.getInstance().saveName(et.getText().toString(),MainActivity.this);
                    showPopup(view);
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
        }else if(view==highscore){
            Intent high = new Intent(this,Highscore.class);
            startActivity(high);
            finish();
        }

    }
    public void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup);
        popupMenu.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.hard: {
                Intent start = new Intent(MainActivity.this, GameActivity.class);
                DataIO.getInstance().saveDifficulty("hard",this);
                startActivity(start);
                finish();
                return true;
            }
            case R.id.easy: {
                Intent start = new Intent(MainActivity.this, GameActivity.class);
                DataIO.getInstance().saveDifficulty("easy",this);
                startActivity(start);
                finish();
                return true;
            }
            default:
                return false;

        }
    }
}