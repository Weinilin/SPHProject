package com.example.sphproject;

import com.example.sphproject.Responses.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataApi {
    @GET("datastore_search")
    Call<DataResponse> getNewsList(@Query("resource_id") String resource_id,
                                   @Query("limit") String limit);
}
