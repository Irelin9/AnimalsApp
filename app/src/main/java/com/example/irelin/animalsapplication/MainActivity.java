package com.example.irelin.animalsapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.irelin.animalsapplication.custom.Constants;
import com.example.irelin.animalsapplication.data.models.Animal;
import com.example.irelin.animalsapplication.view.details.AnimalDetailsFragment;
import com.example.irelin.animalsapplication.view.list.AnimalsListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;

    private List<AnimalsListFragment> animalsListFragments;

    private int currentTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListFragments(savedInstanceState);
        initTabs();
        goToAnimalsList(currentTab);
        tabs.getTabAt(currentTab).select();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (AnimalsListFragment fragment : animalsListFragments) {
            fragment.saveState();
            outState.putSerializable(fragment.getAnimalType(), fragment.getSavedState());
        }
    }

    private void initTabs() {
        tabs.addTab(tabs.newTab().setText(Constants.CAT.toUpperCase()));
        tabs.addTab(tabs.newTab().setText(Constants.DOG.toUpperCase()));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                goToAnimalsList(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initListFragments(Bundle savedInstanceState) {
        animalsListFragments = new ArrayList<>();
        animalsListFragments.add(getFromStackOrNew(Constants.CAT, savedInstanceState));
        animalsListFragments.add(getFromStackOrNew(Constants.DOG, savedInstanceState));
    }

    private AnimalsListFragment getFromStackOrNew(String animalType, Bundle savedInstanceState) {
        AnimalsListFragment fragment = (AnimalsListFragment) getSupportFragmentManager()
                .findFragmentByTag(animalType);
        int position = animalType.equals(Constants.CAT) ? 0 : 1;
        if (fragment != null) {
            currentTab = position;
            return fragment;
        }
        Map<String, Object> savedState = savedInstanceState == null ? null
                : (Map<String, Object>) savedInstanceState.getSerializable(animalType);
        return AnimalsListFragment.newInstance(animalType, savedState);
    }

    public void goToAnimalsList(int position) {
        AnimalsListFragment fragment = animalsListFragments.get(position);
        openFragment(fragment, false, getTagByPosition(position));
    }

    public void goToAnimalsDetails(Animal animal) {
        openFragment(AnimalDetailsFragment.newInstance(animal), true, null);
    }

    private void openFragment(Fragment fragment, boolean addToBackStack, String tag) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, tag);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    private String getTagByPosition(int position) {
        return position == 0 ? Constants.CAT : Constants.DOG;
    }

    public void setTabsVisibility(boolean isVisible) {
        tabs.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
