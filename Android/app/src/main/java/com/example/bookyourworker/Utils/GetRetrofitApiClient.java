package com.example.bookyourworker.Utils;

import com.example.bookyourworker.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRetrofitApiClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null|| !retrofit.baseUrl().toString().equals(baseUrl)) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient;
            if(BuildConfig.DEBUG){
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(2, TimeUnit.MINUTES)
                        .addInterceptor(loggingInterceptor)
                        .readTimeout(2, TimeUnit.MINUTES)
                        .writeTimeout(2, TimeUnit.MINUTES)
                        .build();
            }else {
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(2, TimeUnit.MINUTES)
                        .readTimeout(2, TimeUnit.MINUTES)
                        .writeTimeout(2, TimeUnit.MINUTES)
                        .build();
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

