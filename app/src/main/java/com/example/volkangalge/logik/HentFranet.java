package com.example.volkangalge.logik;

import java.io.IOException;
import java.util.ArrayList;

public interface HentFranet {
    public void registrer(Ord ord);
    public void fjern(Ord ord);
    public void opdaterOrd(ArrayList<String> nyeOrd);
    public String hentUrl(String url) throws IOException;

}
