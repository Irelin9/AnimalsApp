package com.example.irelin.animalsapplication.view.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.irelin.animalsapplication.MainActivity;
import com.example.irelin.animalsapplication.custom.AnimalsApplication;
import com.example.irelin.animalsapplication.custom.PresenterStore;
import com.example.irelin.animalsapplication.custom.ViewIdHolder;

import java.util.HashMap;


public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment implements IBaseView {
    protected static final String VIEW_ID = "VIEW_ID";

    private Presenter presenter;

    private int viewId;

    PresenterStore presenterStore;

    ViewIdHolder viewIdHolder;

    public BaseFragment() {
        presenterStore = AnimalsApplication.getAppComponent().getPresenterStore();
        viewIdHolder = AnimalsApplication.getAppComponent().getViewIdHolder();
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewId == 0) {
            initViewId();
        }
        if (presenter == null) {
            initPresenter();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getArguments() != null) {
            getArguments().putInt(VIEW_ID, viewId);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    private void initViewId() {
        if (getArguments() != null) {
            viewId = getArguments().getInt(VIEW_ID);
        }
        if (viewId == 0){
            viewId = viewIdHolder.getUniqueId();
        }
    }

    private void initPresenter() {
        presenter = presenterStore.get(viewId);
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.bind(this);
    }

    public void saveState() {
        presenterStore.put(viewId, presenter);
    }

    public HashMap<String, Object> getSavedState() {
        HashMap<String, Object> state = new HashMap<>();
        state.put(VIEW_ID, getArguments().getInt(VIEW_ID, viewId));
        return state;
    }

    protected abstract Presenter createPresenter();
}
