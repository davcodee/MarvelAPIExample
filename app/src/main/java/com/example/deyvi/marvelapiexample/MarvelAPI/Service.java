package com.example.deyvi.marvelapiexample.MarvelAPI;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Service {

    @GET(Constants.REST_ENDPOINT)
    Call<JsonObject> getDataComics(@Query("apikey") String apikey
            ,@Query("ts") String ts,@Query("hash") String hash );
}
