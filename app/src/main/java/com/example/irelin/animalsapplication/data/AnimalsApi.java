package com.example.irelin.animalsapplication.data;


import com.example.irelin.animalsapplication.data.models.AnimalsResponse;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class AnimalsApi {
    private IAnimalsApi animalsApi;

    public AnimalsApi(Retrofit retrofit) {
        animalsApi = retrofit.create(IAnimalsApi.class);
    }

    public Single<AnimalsResponse> getAnimals(String query) {
        return animalsApi.getAnimals(query);
    }
}
