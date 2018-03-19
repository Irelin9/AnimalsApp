package com.example.irelin.animalsapplication.view.list;


import com.example.irelin.animalsapplication.custom.AnimalsApplication;
import com.example.irelin.animalsapplication.data.IAnimalsRepository;
import com.example.irelin.animalsapplication.data.models.Animal;
import com.example.irelin.animalsapplication.view.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AnimalsListPresenter extends BasePresenter<IAnimalsListView> {
    @Inject
    IAnimalsRepository animalsRepository;

    private CompositeDisposable disposable = new CompositeDisposable();

    private List<Animal> animals;

    AnimalsListPresenter() {
        AnimalsApplication.getAppComponent().inject(this);
    }

    void loadAnimals(String animalsType) {
        if (animals != null && !animals.isEmpty()) {
            getView().onAnimalsLoaded(animals);
        }
        Single<List<Animal>> animalsObservable = animalsRepository.getAnimals(animalsType);
        disposable.add(animalsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        result -> {
                            animals = result;
                            getView().onAnimalsLoaded(animals);
                        },
                        throwable -> getView().showError("some error")
        ));
    }

    @Override
    public void unsubscribe() {
        disposable.dispose();
    }
}
