package com.example.irelin.animalsapplication.custom;


import com.example.irelin.animalsapplication.view.base.BasePresenter;

import java.util.HashMap;
import java.util.Map;

public class PresenterStore {
    private Map<Integer, BasePresenter> presentersMap = new HashMap<>();

    public <T extends BasePresenter> T get(Integer viewId) {
        try {
            return (T) presentersMap.get(viewId);
        } catch (Exception e) {
            return null;
        }
    }

    public void put(Integer viewId, BasePresenter presenter) {
        presentersMap.put(viewId, presenter);
    }
}
