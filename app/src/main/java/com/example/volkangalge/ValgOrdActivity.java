package com.example.volkangalge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.volkangalge.logik.HentFraArk;
import com.example.volkangalge.logik.ValgtOrd;

public class ValgOrdActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valg_ord);

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        ArrayAdapter adapter;
        if(isConnected) {
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1,
                    HentFraArk.getInstance().getAlleOrd().toArray());
        }else {
            ValgtOrd offlineOrd = new ValgtOrd();
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1,
                    offlineOrd.getAlleOrd().toArray());
        }

        ListView listView = findViewById(R.id.ordlist);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }
    public void onItemClick(AdapterView<?> liste, View v, int position, long id) {
       // Toast.makeText(this, "Klik på " + liste.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        String ordvalgt = liste.getItemAtPosition(position).toString();
        Intent game = new Intent(ValgOrdActivity.this,GameActivity.class);
        game.putExtra("ordet",ordvalgt);
        startActivity(game);
        finish();
    }
    }

