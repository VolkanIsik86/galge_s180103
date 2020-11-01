package com.example.volkangalge.logik;

import java.util.ArrayList;

public class SpillerScores {
    public ArrayList<Spiller> scores;

    public SpillerScores() {
        scores = new ArrayList<>();
    }

    public ArrayList<Spiller> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Spiller> scores) {
        this.scores = scores;
    }
}
