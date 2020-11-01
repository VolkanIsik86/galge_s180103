package com.example.volkangalge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class MainAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    Button[] keys;
    String[] alphabet;

    public MainAdapter(Context context, Button[] keys,String[] alphabet) {
        this.context=context;
        this.keys = keys;
        this.alphabet = alphabet;
    }

    @Override
    public int getCount() {
        return keys.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater==null){
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView = inflater.inflate(R.layout.knapper,null);
        }

        Button button = convertView.findViewById(R.id.kna);
        button.setBackgroundResource(R.drawable.headstone);
        button.setText(alphabet[position]);
      //  button.setOnClickListener(context);

        return convertView;
    }
}
