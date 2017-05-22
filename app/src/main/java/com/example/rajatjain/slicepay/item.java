package com.example.rajatjain.slicepay;

import android.graphics.Bitmap;

public class item {
    private Bitmap image;


    public item(Bitmap image) {
        super();
        this.image = image;

    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
