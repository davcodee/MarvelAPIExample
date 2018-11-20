package com.example.deyvi.marvelapiexample.Model;

import java.util.ArrayList;

public class Comic {
    public String title;
    public String variantDescription;
    public String description;
    public String imageComic;

    public Comic(String title, String variantDescription, String description, String imageComic) {
        this.title = title;
        this.variantDescription = variantDescription;
        this.description = description;
        this.imageComic = imageComic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageComic() {
        return imageComic;
    }

    public void setImageComic(String imageComic) {
        this.imageComic = imageComic;
    }
}
