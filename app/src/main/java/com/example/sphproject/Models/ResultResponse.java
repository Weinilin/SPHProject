package com.example.sphproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultResponse implements Parcelable {
    @SerializedName("resource_id")
    public String resource_id;

    @SerializedName("fields")
    public ArrayList<FieldsResponse> fields;

    @SerializedName("records")
    public ArrayList<RecordsResponse> records;

    @SerializedName("_links")
    public LinkdResponse links;

    @SerializedName("limit")
    public int limit;

    @SerializedName("total")
    public int total;

    protected ResultResponse(Parcel in) {
        resource_id = in.readString();
        limit = in.readInt();
        total = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resource_id);
        dest.writeInt(limit);
        dest.writeInt(total);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultResponse> CREATOR = new Creator<ResultResponse>() {
        @Override
        public ResultResponse createFromParcel(Parcel in) {
            return new ResultResponse(in);
        }

        @Override
        public ResultResponse[] newArray(int size) {
            return new ResultResponse[size];
        }
    };
}
