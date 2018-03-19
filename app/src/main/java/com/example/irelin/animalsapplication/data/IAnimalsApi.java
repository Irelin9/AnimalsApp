package com.example.irelin.animalsapplication.data;


import com.example.irelin.animalsapplication.data.models.AnimalsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAnimalsApi {
    @GET("api.php")
    Single<AnimalsResponse> getAnimals(@Query("query") String query);
}
