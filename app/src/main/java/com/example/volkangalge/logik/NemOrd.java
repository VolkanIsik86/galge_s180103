package com.example.volkangalge.logik;

import java.util.ArrayList;
import java.util.Random;

/**
 * Den nemme ord for at spille spillet med. læg mærke til at de lange ord er nemmere at gætte på.
 * Denne klasse er en subscriber i Observer pattern
 */
public class NemOrd implements Ord {
    ArrayList<String> nemmeOrd;

    public NemOrd() {
        nemmeOrd = new ArrayList<>();
        nemmeOrd.add("computer");
        nemmeOrd.add("programmering");
        nemmeOrd.add("motorvej");
        nemmeOrd.add("busrute");
        nemmeOrd.add("gangsti");
        nemmeOrd.add("skovsnegl");
        nemmeOrd.add("solsort");
        nemmeOrd.add("nitten");
    }

    /**
     * Vælger en tilfældig ord fra listen
     * @return tilfældig ord fra listen
     */
    @Override
    public String randomOrd() {
        int antalOrd = nemmeOrd.size();
        Random random = new Random();
        return nemmeOrd.get(random.nextInt(antalOrd));
    }
    /**
     * Opdatering af ordliste
     * @param muligeord De ord som listen skal opdateres med
     */
    @Override
    public void update(ArrayList<String> muligeord) {
        for (int i = 0; i <muligeord.size() ; i++) {
            if(muligeord.get(i).length()>=6)
            nemmeOrd.add(muligeord.get(i));
        }
    }
}
