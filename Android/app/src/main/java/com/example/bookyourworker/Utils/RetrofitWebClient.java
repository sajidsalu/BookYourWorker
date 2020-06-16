package com.example.bookyourworker.Utils;

import android.content.Context;

import com.example.bookyourworker.Model.RestApiResponse;
import com.example.bookyourworker.Services.ResponseCallbackCustom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitWebClient {

    private int SOCKET_TIMEOUT_MS =30000;

    public RetrofitWebClient(Context context){

        SOCKET_TIMEOUT_MS = Math.abs((int) 2 * 1000);
    }


    //// Method used for get API.
    public void getApiResponse(Context context, String url, final ResponseCallbackCustom callback) {
        Retrofit retrofit = GetRetrofitApiClient.getClient(ViewConstants.BASE_URL);
        RetrofitApiClient retrofitApiClient = retrofit.create(RetrofitApiClient.class);
        retrofitApiClient.executeRestGetWithUrl(ViewConstants.BASE_URL + url).enqueue(new Callback<RestApiResponse>(){
            @Override
            public void onResponse(Call<RestApiResponse> call, Response<RestApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body()!=null){
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            gsonBuilder.serializeNulls();
                            Gson gson = gsonBuilder.create();
                            callback.onSuccess(gson.toJson(response.body()));
                        } else{
                        callback.onFailure("");
                    }
                } else{
                    callback.onFailure("");
                }
            }

            @Override
            public void onFailure(Call<RestApiResponse> call, Throwable t) {
                callback.onFailure("");
            }
        });
    }



}
