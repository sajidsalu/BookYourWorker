package com.example.bookyourworker.Utils;


import com.example.bookyourworker.Model.RestApiResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

import org.json.JSONObject;

public interface RetrofitApiClient {

    @GET()
    Call<RestApiResponse> executeRestGetWithUrl(@Url String url);


    @POST("{path}")
    Call<RestApiResponse> executeRestPost(@Path(value = "path", encoded = true) String path);

}