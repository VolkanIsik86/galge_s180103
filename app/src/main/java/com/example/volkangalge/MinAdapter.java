package com.example.volkangalge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.volkangalge.logik.SpillerScores;
import java.util.Collections;
//https://guides.codepath.com/android/Using-a-BaseAdapter-with-ListView

public class MinAdapter extends BaseAdapter {
    Context context;
    SpillerScores spillerScores;

    public MinAdapter(Context context, SpillerScores spillerScores) {
        this.context = context;
        this.spillerScores = spillerScores;
    }

    @Override
    public int getCount() {
        return spillerScores.scores.size();
    }

    @Override
    public Object getItem(int position) {
        return spillerScores.scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.scores, parent, false);
        }

        TextView spillernavn = (TextView) convertView.findViewById(R.id.spillernavn);
        TextView spillerscore = (TextView) convertView.findViewById(R.id.spillerscore);

        // sorter i spiller liste så den højeste score er den første i listen der skal vises
        Collections.sort(spillerScores.scores);

        String navnogpos= String.valueOf(position+1)+": "+spillerScores.scores.get(position).getNavn();
        spillernavn.setText(navnogpos);
        spillerscore.setText(String.valueOf(spillerScores.scores.get(position).getScore()));

        return convertView;
    }
}
