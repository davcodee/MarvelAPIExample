package com.example.deyvi.marvelapiexample;

import java.security.NoSuchAlgorithmException;

class MarvelApiException extends Throwable {
    private final int httpCode;
    private final String marvelCode;

    public MarvelApiException(int httpCode, String marvelCode, String description, Throwable cause) {
        super(description, cause);
        this.httpCode = httpCode;
        this.marvelCode = marvelCode;
    }

    public MarvelApiException(String message, Throwable cause) {
        this(-1, "", message, cause);
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getMarvelCode() {
        return marvelCode;
    }

}
