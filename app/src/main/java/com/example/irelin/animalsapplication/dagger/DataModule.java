package com.example.irelin.animalsapplication.dagger;

import com.example.irelin.animalsapplication.custom.Constants;
import com.example.irelin.animalsapplication.custom.PresenterStore;
import com.example.irelin.animalsapplication.custom.ViewIdHolder;
import com.example.irelin.animalsapplication.data.AnimalsRepository;
import com.example.irelin.animalsapplication.data.IAnimalsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_ANIMALS_URL)
                .build();
    }

    @Provides
    IAnimalsRepository provideAnimalsRepository(Retrofit retrofit) {
        return new AnimalsRepository(retrofit);
    }

    @Provides
    @Singleton
    PresenterStore providePresenterStore() {
        return new PresenterStore();
    }

    @Provides
    @Singleton
    ViewIdHolder provideViewIdHolder() {
        return new ViewIdHolder();
    }
}
