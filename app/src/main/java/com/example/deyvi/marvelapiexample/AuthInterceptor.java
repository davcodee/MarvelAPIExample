package com.example.deyvi.marvelapiexample;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    /*LLave pública*/
    private String publicKey;
    /*Llave privada*/
    private String privateKey;
    /*TimeProvider*/
    private TimeProvide timeProvider;

    /*Parametro que se ocuparán en el request   */
    private static final String TIMESTAMP_KEY = "ts";
    private static final String HASH_KEY = "hash";
    private static final String APIKEY_KEY = "apikey";
    private final HashGenerator authHashGenerator = new HashGenerator();

    public AuthInterceptor(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
       //String timestamp = String.valueOf(timeProvider.currentTimeMillis());
        String hash = null;
        try {
            hash = authHashGenerator.generateHash("1", publicKey, privateKey);
        } catch (MarvelApiException e) {
            e.printStackTrace();
        }
        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(TIMESTAMP_KEY, "1")
                .addQueryParameter(APIKEY_KEY, publicKey)
                .addQueryParameter(HASH_KEY, hash)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
