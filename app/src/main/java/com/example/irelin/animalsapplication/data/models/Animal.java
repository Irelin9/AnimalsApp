package com.example.irelin.animalsapplication.data.models;


import java.io.Serializable;

public class Animal implements Serializable {
    String url;
    String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
