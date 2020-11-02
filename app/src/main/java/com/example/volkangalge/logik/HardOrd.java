package com.example.volkangalge.logik;

import java.util.ArrayList;
import java.util.Random;

public class HardOrd implements Ord {

    ArrayList<String> hardOrd;

    public HardOrd() {
        hardOrd = new ArrayList<>();
        hardOrd.add("bil");
        hardOrd.add("hest");
        hardOrd.add("sko");
        hardOrd.add("løve");
        hardOrd.add("router");
    }
    @Override
    public String randomOrd() {
        int antalOrd = hardOrd.size();
        Random random = new Random();
        return hardOrd.get(random.nextInt(antalOrd));
    }

    @Override
    public void update(ArrayList<String> muligeord) {
        for (int i = 0; i <muligeord.size() ; i++) {
            if(muligeord.get(i).length()<6)
            hardOrd.add(muligeord.get(i));
        }
    }
}
