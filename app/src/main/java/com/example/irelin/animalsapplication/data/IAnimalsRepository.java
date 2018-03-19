package com.example.irelin.animalsapplication.data;


import com.example.irelin.animalsapplication.data.models.Animal;

import java.util.List;

import io.reactivex.Single;

public interface IAnimalsRepository {
    Single<List<Animal>> getAnimals(String animalsType);
}
