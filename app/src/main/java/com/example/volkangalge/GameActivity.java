package com.example.volkangalge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volkangalge.logik.Galgelogik;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        logik = new Galgelogik();
        logik.nulstil();
        ord=findViewById(R.id.galgeord);

        //Henter ord fra dr fra bagrundstråd
        bgThread.execute(() -> {
            try{
                logik.hentOrdFraDr();
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
        a = findViewById(R.id.A);
        b = findViewById(R.id.B);
        c = findViewById(R.id.C);
        d = findViewById(R.id.D);
        e = findViewById(R.id.E);
        f = findViewById(R.id.F);
        g = findViewById(R.id.G);
        h = findViewById(R.id.H);
        i = findViewById(R.id.I);
        j = findViewById(R.id.J);
        k = findViewById(R.id.K);
        l = findViewById(R.id.L);
        m = findViewById(R.id.M);
        n = findViewById(R.id.N);
        o = findViewById(R.id.O);
        p = findViewById(R.id.P);
        q = findViewById(R.id.Q);
        r = findViewById(R.id.R);
        s = findViewById(R.id.S);
        t = findViewById(R.id.T);
        u = findViewById(R.id.U);
        v = findViewById(R.id.V);
        w = findViewById(R.id.W);
        x = findViewById(R.id.X);
        y = findViewById(R.id.Y);
        z = findViewById(R.id.Z);
        æ = findViewById(R.id.Æ);
        ø = findViewById(R.id.Ø);
        å = findViewById(R.id.Å);
        returnbut = findViewById(R.id.returnbut);

        //alle knapper sættes i en arraylist.
        buttons.add(a);
        buttons.add(b);
        buttons.add(c);
        buttons.add(d);
        buttons.add(e);
        buttons.add(f);
        buttons.add(g);
        buttons.add(h);
        buttons.add(i);
        buttons.add(j);
        buttons.add(k);
        buttons.add(l);
        buttons.add(m);
        buttons.add(n);
        buttons.add(o);
        buttons.add(p);
        buttons.add(q);
        buttons.add(r);
        buttons.add(s);
        buttons.add(t);
        buttons.add(u);
        buttons.add(v);
        buttons.add(w);
        buttons.add(x);
        buttons.add(y);
        buttons.add(z);
        buttons.add(æ);
        buttons.add(ø);
        buttons.add(å);
        buttons.add(returnbut);
    // oncliklistener til alle knapper
        for (int i = 0; i <30 ; i++) {
            buttons.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        //aktivirtet afsluttes hvis man trykker på returner knap
        if(view==returnbut){
            Intent start = new Intent(this,MainActivity.class);
            this.finish();
            startActivity(start);
        }else{

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
                if(logik.erSpilletTabt()) spilletSlutDialog(true);
                if(logik.erSpilletVundet()) spilletSlutDialog(false);
            }
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

    /**
     * Når spillet er slut så kommer der en dialog der spørger om man vil fortsætte. Den nuværende aktivitet lukkes
     * @param status
     */
    void spilletSlutDialog(boolean status){
        String besked;
        if(status) {
            besked= "Du har tabt spillet!?!\nVil du spille igen?";
        }else {
            besked = "Du har vundet spillet!?!\nVil du spille igen?";
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(besked);
        dialog.setPositiveButton("Ja", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent spilingen = new Intent(GameActivity.this,GameActivity.class);
                startActivity(spilingen);
                finish();
            }
        });
        dialog.setNegativeButton("Nej", new AlertDialog.OnClickListener(){
            public void onClick(DialogInterface arg0, int arg1) {
                Intent spilingen = new Intent(GameActivity.this,MainActivity.class);
                startActivity(spilingen);
                finish();
            }
        });
        dialog.show().getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(135,147,154)) {
        });

    }


}