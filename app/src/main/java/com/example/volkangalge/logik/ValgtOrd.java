package com.example.volkangalge.logik;

import java.util.ArrayList;

public class ValgtOrd implements Ord{
    ArrayList<String> alleOrd;

    public ValgtOrd() {
        alleOrd = new ArrayList<>();
        alleOrd.add("computer");
        alleOrd.add("programmering");
        alleOrd.add("motorvej");
        alleOrd.add("busrute");
        alleOrd.add("gangsti");
        alleOrd.add("skovsnegl");
        alleOrd.add("solsort");
        alleOrd.add("nitten");
        alleOrd.add("bil");
        alleOrd.add("hest");
        alleOrd.add("sko");
        alleOrd.add("løve");
        alleOrd.add("router");
    }

    @Override
    public String randomOrd() {
        return null;
    }

    @Override
    public void update(ArrayList<String> muligeord) {
        for (int i = 0; i < muligeord.size() ; i++) {
            alleOrd.add(muligeord.get(i));
        }
    }

    public ArrayList<String> getAlleOrd() {
        return alleOrd;
    }

    public void setAlleOrd(ArrayList<String> alleOrd) {
        this.alleOrd = alleOrd;
    }
}
