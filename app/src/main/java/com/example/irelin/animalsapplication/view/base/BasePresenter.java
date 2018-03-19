package com.example.irelin.animalsapplication.view.base;


public abstract class BasePresenter<View extends IBaseView> {
    private View view;

    protected View getView() {
        return view;
    }

    void bind(View view) {
        this.view = view;
    }

    public abstract void unsubscribe();
}
