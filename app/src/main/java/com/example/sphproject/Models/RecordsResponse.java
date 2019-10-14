package com.example.sphproject.Models;

import com.google.gson.annotations.SerializedName;

public class RecordsResponse {

    public RecordsResponse(String volume_of_mobile_data, String quarter, int id) {
        this.volume_of_mobile_data = volume_of_mobile_data;
        this.quarter = quarter;
        this.id = id;
    }
    @SerializedName("volume_of_mobile_data")
    public String volume_of_mobile_data;

    @SerializedName("quarter")
    public String quarter;

    @SerializedName("_id")
    public int id;
}
