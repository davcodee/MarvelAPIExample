package com.example.deyvi.marvelapiexample.MarvelAPI;


import com.example.deyvi.marvelapiexample.AuthInterceptor;


import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*Clase donde vamos a manejar nuestro servicio*/
public class RestApiAdapter {

    public Service getComicService() {

        /*Ya no vamos a ocupar nustro  okhttpclient/
        /*Hay que enviar nuestros headers
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(Constants.APIKEY,Constants.PRIVATEHEY))
                .build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);

    }

}
