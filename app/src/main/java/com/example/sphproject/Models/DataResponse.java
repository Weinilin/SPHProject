package com.example.sphproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("help")
    public String help;

    @SerializedName("success")
    public Boolean success;

    @SerializedName("result")
    public ResultResponse result;

    public DataResponse(String help, Boolean success, ResultResponse resultResponse) {
        this.help =  help;
        this.success = success;
        result = resultResponse;
    }

}
