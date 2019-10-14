package com.example.sphproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataResponse implements Parcelable {

    @SerializedName("help")
    public String help;

    @SerializedName("success")
    public Boolean success;

    @SerializedName("result")
    public ResultResponse result;

    protected DataResponse(Parcel in) {
        help = in.readString();
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
    }

    public static final Creator<DataResponse> CREATOR = new Creator<DataResponse>() {
        @Override
        public DataResponse createFromParcel(Parcel in) {
            return new DataResponse(in);
        }

        @Override
        public DataResponse[] newArray(int size) {
            return new DataResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(help);
        parcel.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
    }
}
