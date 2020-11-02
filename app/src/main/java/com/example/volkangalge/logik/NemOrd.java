package com.example.volkangalge.logik;

import java.util.ArrayList;
import java.util.Random;

public class NemOrd implements Ord {
    ArrayList<String> nemmeOrd;

    public NemOrd() {
        nemmeOrd = new ArrayList<>();
        nemmeOrd.add("bil");
        nemmeOrd.add("computer");
        nemmeOrd.add("programmering");
        nemmeOrd.add("motorvej");
        nemmeOrd.add("busrute");
        nemmeOrd.add("gangsti");
        nemmeOrd.add("skovsnegl");
        nemmeOrd.add("solsort");
        nemmeOrd.add("nitten");
    }

    @Override
    public String randomOrd() {
        int antalOrd = nemmeOrd.size();
        Random random = new Random();
        return nemmeOrd.get(random.nextInt(antalOrd));
    }

    @Override
    public void update(ArrayList<String> muligeord) {
        for (int i = 0; i <muligeord.size() ; i++) {
            if(muligeord.get(i).length()>=6)
            nemmeOrd.add(muligeord.get(i));
        }
    }
}
