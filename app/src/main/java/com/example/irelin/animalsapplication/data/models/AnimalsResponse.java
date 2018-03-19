package com.example.irelin.animalsapplication.data.models;


import java.util.List;

public class AnimalsResponse {
    String message;
    List<Animal> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Animal> getData() {
        return data;
    }

    public void setData(List<Animal> data) {
        this.data = data;
    }
}
