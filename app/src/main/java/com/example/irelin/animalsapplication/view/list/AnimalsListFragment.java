package com.example.irelin.animalsapplication.view.list;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.irelin.animalsapplication.R;
import com.example.irelin.animalsapplication.data.models.Animal;
import com.example.irelin.animalsapplication.view.adapter.AnimalsAdapter;
import com.example.irelin.animalsapplication.view.base.BaseFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalsListFragment extends BaseFragment<AnimalsListPresenter> implements IAnimalsListView {
    private static final String ANIMAL_TYPE = "ANIMAL_TYPE";
    private static final String SCROLL_STATE = "SCROLL_STATE";

    @BindView(R.id.list)
    RecyclerView rvAnimals;

    private String animalType;
    private Parcelable scrollState;

    private AnimalsAdapter animalsAdapter;
    private LinearLayoutManager layoutManager;

    public AnimalsListFragment() {
    }

    public String getAnimalType() {
        return getArguments().getString(ANIMAL_TYPE, animalType);
    }

    public static AnimalsListFragment newInstance(String animalType, Map<String, Object> savedState) {
        AnimalsListFragment fragment = new AnimalsListFragment();
        fragment.setArguments(createArguments(animalType, savedState));
        return fragment;
    }

    private static Bundle createArguments(String animalType, Map<String, Object> savedState) {
        Bundle args = new Bundle();
        args.putString(ANIMAL_TYPE, animalType);
        if (savedState == null) return args;

        if (savedState.containsKey(VIEW_ID)) {
            args.putInt(VIEW_ID, (int) savedState.get(VIEW_ID));
        }
        if (savedState.containsKey(SCROLL_STATE)) {
            args.putParcelable(SCROLL_STATE, (Parcelable) savedState.get(SCROLL_STATE));
        }
        return args;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            animalType = getArguments().getString(ANIMAL_TYPE);
            scrollState = getArguments().getParcelable(SCROLL_STATE);
        }
    }

    @Override
    protected AnimalsListPresenter createPresenter() {
        return new AnimalsListPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animals_list, container, false);
        ButterKnife.bind(this, view);
        layoutManager = new LinearLayoutManager(getContext());
        rvAnimals.setLayoutManager(layoutManager);
        if (animalsAdapter == null) {
            getPresenter().loadAnimals(animalType);
        } else {
            rvAnimals.setAdapter(animalsAdapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getMainActivity().selectTab(animalType);
    }

    @Override
    public HashMap<String, Object> getSavedState() {
        HashMap<String, Object> state = super.getSavedState();
        state.put(ANIMAL_TYPE, getArguments().getString(ANIMAL_TYPE, animalType));
        state.put(SCROLL_STATE, layoutManager != null
                ? layoutManager.onSaveInstanceState()
                : getArguments().getParcelable(SCROLL_STATE));
        return state;
    }

    @Override
    public void onAnimalsLoaded(List<Animal> animals) {
        animalsAdapter = new AnimalsAdapter(animals, item -> getMainActivity().goToAnimalsDetails(item));
        rvAnimals.setAdapter(animalsAdapter);
        if (scrollState != null) {
            layoutManager.onRestoreInstanceState(scrollState);
        }
    }
}
