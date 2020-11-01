package com.example.volkangalge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volkangalge.logik.Galgelogik;
import com.example.volkangalge.logik.Spiller;
import com.example.volkangalge.logik.SpillerScores;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, æ, ø, å, returnbut;
    Galgelogik logik;
    TextView ord;
    ImageView biledet;
    ArrayList<Button> buttons;
    String galgeordet;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        intent = getIntent();
        logik = new Galgelogik();
        logik.nulstil();
        ord=findViewById(R.id.galgeord);

        //Henter ord fra dr fra bagrundstråd
        bgThread.execute(() -> {
            try{
                logik.hentOrdFraRegneark("12");
                uiThread.post(() -> {
                    ord.setText(logik.getSynligtOrd());
                });

            }catch (Exception e){
                e.printStackTrace();
                uiThread.post(() -> {
                    ord.setText("Der opstod en fejl ved hentning af ordet");
                });
            }
        });

        biledet = findViewById(R.id.billedet);
        buttons = new ArrayList<>();
// initaliserer alle knapper tastetur og return knap - denne kode laves om til anden aflevering til lineær layou
        final float scale = this.getResources().getDisplayMetrics().density;
        int pixels = (int) (40 * scale + 0.5f);

        String[] alphabetet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Æ", "Ø", "Å", ""};

        int iter = 0;
        buttons = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Button button = new Button(this);
            button.setOnClickListener(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, pixels,1));
            button.setText(alphabetet[i]);
            button.setBackgroundResource(R.drawable.headstone);
            buttons.add(button);
        }
        buttons.get(29).setVisibility(View.INVISIBLE);

        LinearLayout linearLayout = findViewById(R.id.keyboard);
        for (int i = 0; i < 5; i++) {
            LinearLayout række = new LinearLayout(this);
            række.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 6; j++) {
                række.addView(buttons.get(iter));
                iter++;
            }
            linearLayout.addView(række);
        }
    }

    public void onBackPressed(){
        Intent start = new Intent(this,MainActivity.class);
        this.finish();
        startActivity(start);
    }

    @Override
    public void onClick(View view) {


            //kontroller vi om den tastede bogstav er korrekt
            Button trykket = null;
            for (int i = 0; i < 30 ; i++) {
                if (view==buttons.get(i)){
                    trykket=buttons.get(i);
                }
            }
            assert trykket != null;
            gæt(trykket);

            //vi afslutter spiller hvis det er slut eller vundet.
            if(logik.erSpilletSlut()){
                if(logik.erSpilletTabt()) slutspilllet(false);
                if(logik.erSpilletVundet()) slutspilllet(true);
            }

    }

    /**
     * Metoden benytter sig af galgelogik og gætter på en bogstav og skifter billedet hvis det er forkert, ordet på skærmen opdateres.
     * @param knap Knappen der blev trykket.
     */
    void gæt(Button knap) {
        logik.gætBogstav(knap.getText().toString().toLowerCase());
        if (ord.getText().toString().equalsIgnoreCase(logik.getSynligtOrd())) {
               skiftbillede(logik.getAntalForkerteBogstaver(),knap);
               knap.setClickable(false);
            }else{
            ord.setText(logik.getSynligtOrd());
            knap.setClickable(false);
            knap.setBackgroundResource(R.drawable.headstonegreen);
        }

        }

    /**
     * metoden skifter billedet på skærmen og derefter skifter den knappen.
     * @param antalForkert billedet iforhold til antal forkerte
     * @param knap knappen der blev trykket
     */
    void skiftbillede(int antalForkert, Button knap){

        switch (antalForkert){
            case 1:
                biledet.setImageResource(R.drawable.forkert1);
                knap.setBackgroundResource(R.drawable.headstonegold);
                break;
            case 2:
                biledet.setImageResource(R.drawable.forkert2);
                knap.setBackgroundResource(R.drawable.headstonegold);
                break;
            case 3:
                biledet.setImageResource(R.drawable.forkert3);
                knap.setBackgroundResource(R.drawable.headstonegold);
                break;
            case 4:
                biledet.setImageResource(R.drawable.forkert4);
                knap.setBackgroundResource(R.drawable.headstonegold);
                break;
            case 5:
                biledet.setImageResource(R.drawable.forkert5);
                knap.setBackgroundResource(R.drawable.headstonegold);
                break;
            case 6:
                biledet.setImageResource(R.drawable.forkert6);
                knap.setBackgroundResource(R.drawable.headstonegold);
                break;
        }

    }

   public void slutspilllet(boolean vundet){
        if(vundet){
            Spiller spiller = new Spiller(intent.getStringExtra("spillernavn"));
            spiller.setScore(logik.getOrdet().length()*(7-logik.getAntalForkerteBogstaver()));


            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String allescores = prefs.getString("allescore","{\"scores\":[{\"navn\":\"noname\",\"score\":0}]}");


            Gson gson = new Gson();
            SpillerScores scores = gson.fromJson(allescores,SpillerScores.class);
            scores.scores.add(spiller);
            allescores = gson.toJson(scores);
            prefs.edit().putString("allescore",allescores).apply();


            Intent win = new Intent(this,Vundet.class);
            win.putExtra("antalforsøg",Integer.toString(logik.getAntalForkerteBogstaver()));
            startActivity(win);
            finish();
        }
        if(!vundet){
            Intent lose = new Intent(this,Tabt.class);
            lose.putExtra("ordet",logik.getOrdet());
            startActivity(lose);
            finish();
        }


   }


}