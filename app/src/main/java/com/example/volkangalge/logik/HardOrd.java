package com.example.volkangalge.logik;

import java.util.ArrayList;
import java.util.Random;

/**
 * Den svær ord for at spille spillet med. læg mærke til at de korte er sværre at gætte på.
 * Denne klasse er en subscriber i Observer pattern
 */
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

    /**
     * Vælger en tilfældig ord fra listen
     * @return tilfældig ord fra listen
     */
    @Override
    public String randomOrd() {
        int antalOrd = hardOrd.size();
        Random random = new Random();
        return hardOrd.get(random.nextInt(antalOrd));
    }

    /**
     * Opdatering af ordliste
     * @param muligeord De ord som listen skal opdateres med
     */
    @Override
    public void update(ArrayList<String> muligeord) {
        for (int i = 0; i <muligeord.size() ; i++) {
            if(muligeord.get(i).length()<6)
            hardOrd.add(muligeord.get(i));
        }
    }
}
