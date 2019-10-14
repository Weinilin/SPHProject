package com.example.sphproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LinkdResponse implements Parcelable {

    @SerializedName("start")
    public String start;

    @SerializedName("next")
    public String next;

    protected LinkdResponse(Parcel in) {
        start = in.readString();
        next = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(start);
        dest.writeString(next);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LinkdResponse> CREATOR = new Creator<LinkdResponse>() {
        @Override
        public LinkdResponse createFromParcel(Parcel in) {
            return new LinkdResponse(in);
        }

        @Override
        public LinkdResponse[] newArray(int size) {
            return new LinkdResponse[size];
        }
    };
}
