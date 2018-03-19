package com.example.irelin.animalsapplication.dagger;

import com.example.irelin.animalsapplication.custom.PresenterStore;
import com.example.irelin.animalsapplication.custom.ViewIdHolder;
import com.example.irelin.animalsapplication.view.list.AnimalsListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {DataModule.class})
@Singleton
public interface AppComponent {
    void inject(AnimalsListPresenter presenter);
    PresenterStore getPresenterStore();
    ViewIdHolder getViewIdHolder();
}
