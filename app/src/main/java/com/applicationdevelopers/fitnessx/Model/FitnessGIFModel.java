package com.applicationdevelopers.fitnessx.Model;

public class FitnessGIFModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageGIF() {
        return imageGIF;
    }

    public void setImageGIF(String imageGIF) {
        this.imageGIF = imageGIF;
    }

    public FitnessGIFModel(String name, String imageGIF) {
        this.name = name;
        this.imageGIF = imageGIF;
    }

    String name;
    String imageGIF;
}
