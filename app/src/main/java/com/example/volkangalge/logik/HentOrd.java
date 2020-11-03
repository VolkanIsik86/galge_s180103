package com.example.volkangalge.logik;

import java.io.IOException;
import java.util.ArrayList;

public interface HentOrd {
    public void registrer(Ord ord);
    public void fjern(Ord ord);
    public void opdaterOrd(ArrayList<String> nyeOrd);


}
