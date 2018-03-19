package com.example.irelin.animalsapplication.view.list;


import com.example.irelin.animalsapplication.data.models.Animal;
import com.example.irelin.animalsapplication.view.base.IBaseView;

import java.util.List;

public interface IAnimalsListView extends IBaseView {
    void onAnimalsLoaded(List<Animal> animals);
}
