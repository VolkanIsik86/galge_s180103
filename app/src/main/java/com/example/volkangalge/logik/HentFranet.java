package com.example.volkangalge.logik;

import java.util.ArrayList;

public interface HentFranet {
    public void registrer(Ord ord);
    public void fjen(Ord ord);
    public void opdaterOrd(ArrayList<String> nyeOrd);
    public void hentOrdGoogle();

}
