package com.example.irelin.animalsapplication.custom;


import android.app.Application;

import com.example.irelin.animalsapplication.dagger.AppComponent;
import com.example.irelin.animalsapplication.dagger.DaggerAppComponent;
import com.example.irelin.animalsapplication.dagger.DataModule;

public class AnimalsApplication extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .dataModule(new DataModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
