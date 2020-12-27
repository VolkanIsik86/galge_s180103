package com.example.volkangalge.logik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class HentFraArk implements HentOrd,HentFraNet {
    ArrayList<Ord> ordArrayList;
    ArrayList<String> alleOrd;
    public static HentFraArk hentFraArk;

    private HentFraArk() {
        this.ordArrayList = new ArrayList<>();
    }

    public static HentFraArk getInstance(){
        if(hentFraArk == null){
            hentFraArk = new HentFraArk();
        }
        return hentFraArk;
    }

    @Override
    public void registrer(Ord ord) {
        ordArrayList.add(ord);
    }

    @Override
    public void fjern(Ord ord) {
        ordArrayList.remove(ord);
    }

    @Override
    public void opdaterOrd(ArrayList<String> nyeOrd) {
        for (int i = 0; i <ordArrayList.size() ; i++) {
            ordArrayList.get(i).update(nyeOrd);
        }

    }

    public ArrayList<String> getAlleOrd() {
        return alleOrd;
    }

    public void setAlleOrd(ArrayList<String> alleOrd) {
        this.alleOrd = alleOrd;
    }

    @Override
    public String hentUrl(String url) throws IOException {

            System.out.println("Henter data fra " + url);
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            StringBuilder sb = new StringBuilder();
            String linje = br.readLine();
            while (linje != null) {
                sb.append(linje + "\n");
                linje = br.readLine();
            }
            return sb.toString();
        }

    /**
     * Hent ord og sværhedsgrad fra et online regneark. Du kan redigere i regnearket, på adressen
     * https://docs.google.com/spreadsheets/d/1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M/edit?usp=sharing
     * @param sværhedsgrader en streng med de tilladte sværhedsgrader - f.eks "3" for at medtage kun svære ord, eller "12" for alle nemme og halvsvære ord
     * @throws Exception
     */
    public void hentOrdGoogle(String sværhedsgrader) throws Exception{
        ArrayList<String> muligeOrd = new ArrayList<>();

        String id = "1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M";

        System.out.println("Henter data som kommasepareret CSV fra regnearket https://docs.google.com/spreadsheets/d/"+id+"/edit?usp=sharing");

        String data = hentUrl("https://docs.google.com/spreadsheets/d/" + id + "/export?format=csv&id=" + id);
        int linjeNr = 0;

        muligeOrd.clear();
        for (String linje : data.split("\n")) {
            if (linjeNr<20) System.out.println("Læst linje = " + linje); // udskriv de første 20 linjer
            if (linjeNr++ < 1 ) continue; // Spring første linje med kolonnenavnene over
            String[] felter = linje.split(",", -1);// -1 er for at beholde tomme indgange, f.eks. bliver ",,," splittet i et array med 4 tomme strenge
            String sværhedsgrad = felter[0].trim();
            String ordet = felter[1].trim().toLowerCase();
            if (sværhedsgrad.isEmpty() || ordet.isEmpty()) continue; // spring over linjer med tomme ord
            if (!sværhedsgrader.contains(sværhedsgrad)) continue; // filtrér på sværhedsgrader
            System.out.println("Tilføjer "+ordet+", der har sværhedsgrad "+sværhedsgrad);
            muligeOrd.add(ordet);
        }

        System.out.println("muligeOrd = " + muligeOrd);
        alleOrd = muligeOrd;
    }

}

/**
 * Hent ord fra DRs forside (https://dr.dk)

 public void hentOrdFraDr() throws Exception {
 String data = hentUrl("https://dr.dk");
 //System.out.println("data = " + data);

 data = data.substring(data.indexOf("<body")). // fjern headere
 replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
 replaceAll("&#198;", "æ"). // erstat HTML-tegn
 replaceAll("&#230;", "æ"). // erstat HTML-tegn
 replaceAll("&#216;", "ø"). // erstat HTML-tegn
 replaceAll("&#248;", "ø"). // erstat HTML-tegn
 replaceAll("&oslash;", "ø"). // erstat HTML-tegn
 replaceAll("&#229;", "å"). // erstat HTML-tegn
 replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
 replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
 replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

 System.out.println("data = " + data);
 System.out.println("data = " + Arrays.asList(data.split("\\s+")));
 muligeOrd.clear();
 muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

 System.out.println("muligeOrd = " + muligeOrd);
 // nulstil();
 }

 */