package com.halfcigarette.dietitian.beans;

import android.graphics.Bitmap;

/**
 * Created by dongweihang on 2015/11/16.
 */
public class FindExample {
    private Bitmap image;
    private String title;
    private String content;

    public FindExample(Bitmap image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
