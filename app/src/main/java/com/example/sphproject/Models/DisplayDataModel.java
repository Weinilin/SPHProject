package com.example.sphproject.Models;

public class DisplayDataModel {
    public String year;
    public String totalVol;
    public boolean hasDecreaseVol;

    public DisplayDataModel(String year, String totalVol, boolean hasDecreaseVol) {
        this.year = year;
        this.totalVol = totalVol;
        this.hasDecreaseVol = hasDecreaseVol;
    }
}
