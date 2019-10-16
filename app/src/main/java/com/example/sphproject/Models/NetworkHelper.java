package com.example.sphproject.Models;

import com.example.sphproject.DataApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    public static String baseUrl = "https://data.gov.sg/api/action/";

    //set up retrofit
    private Retrofit setRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(this.provideOkHttpClient())
                .build();
    }

    //create the service for current and future api call
    public DataApi getApiService() {
        return setRetrofit().create(DataApi.class);
    }

    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();

        okhttpClientBuilder.readTimeout(60, TimeUnit.SECONDS);
        okhttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(60, TimeUnit.SECONDS);

        return okhttpClientBuilder.build();
    }
}

