package com.example.volkangalge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.volkangalge.logik.DataIO;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import com.example.volkangalge.logik.Galgelogik;
import com.example.volkangalge.logik.HentFraArk;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    Button start, hjælp, afslut,highscore;
    MediaPlayer intro;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//3 knapper initaliseres til hovedmenu. Spillet starter fra start knappen.
        start = findViewById(R.id.start);
        hjælp = findViewById(R.id.hjælp);
        afslut = findViewById(R.id.afslut);
        highscore = findViewById(R.id.Highgamescores);

        intro = MediaPlayer.create(this,R.raw.intro);
        intro.setVolume(1,1);
        intro.start();
        start.setOnClickListener(this);
        hjælp.setOnClickListener(this);
        afslut.setOnClickListener(this);
        highscore.setOnClickListener(this);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

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

                    ProgressDialog dialog = new ProgressDialog(MainActivity.this,R.style.CustomDialogTheme);
                    dialog.setIndeterminate(true); // drejende hjul
                    dialog.setTitle("Ord Hentes");
                    dialog.setMessage("Vent venligst");
                    dialog.show();

                    //Henter ord fra dr fra bagrundstråd
                    bgThread.execute(() -> {
                        try{

                            HentFraArk.getInstance().hentOrdGoogle("12");

                            uiThread.post(() -> {
                                dialog.dismiss();
                                showPopup(view);
                            });

                        }catch (Exception e){
                            e.printStackTrace();
                            uiThread.post(() -> {
                                showPopup(view);
                            });
                        }
                    });

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
            case R.id.valg: {
                Intent start = new Intent(MainActivity.this,ValgOrdActivity.class);
                DataIO.getInstance().saveDifficulty("valgord",this);
                startActivity(start);
                finish();
                return true;
            }
            default:
                return false;

        }
    }
    @Override
    public void onDestroy() {
        intro.stop();
        intro.release();
        super.onDestroy();
    }
}