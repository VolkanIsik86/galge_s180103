package com.example.volkangalge.logik;

public class Spiller implements Comparable{
    String navn;
    int score;

    public Spiller(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        int score = ((Spiller) o).getScore();
        return score - this.getScore();
    }
}
