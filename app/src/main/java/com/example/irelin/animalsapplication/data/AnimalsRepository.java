package com.example.irelin.animalsapplication.data;


import com.example.irelin.animalsapplication.data.models.Animal;
import com.example.irelin.animalsapplication.data.models.AnimalsResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class AnimalsRepository implements IAnimalsRepository {
    private AnimalsApi animalsApi;

    public AnimalsRepository(Retrofit retrofit) {
        animalsApi = new AnimalsApi(retrofit);
    }

    @Override
    public Single<List<Animal>> getAnimals(String animalsType) {
        return animalsApi.getAnimals(animalsType).map(AnimalsResponse::getData);
    }
}
