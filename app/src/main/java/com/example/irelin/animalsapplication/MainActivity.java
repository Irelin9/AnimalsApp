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
    private static final String CURRENT_TAB = "CURRENT_TAB";
    private static final int DETAILS_TAB = -1;

    @BindView(R.id.tabs)
    TabLayout tabs;

    private List<AnimalsListFragment> animalsListFragments;
    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListFragments(savedInstanceState);
        initTabs();
        if (savedInstanceState != null) {
            currentTab = savedInstanceState.getInt(CURRENT_TAB);
        }
        if (currentTab >= 0) {
            openCurrentTab();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_TAB, currentTab);
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
        if (fragment != null) return fragment;
        Map<String, Object> savedState = savedInstanceState == null ? null
                : (Map<String, Object>) savedInstanceState.getSerializable(animalType);
        return AnimalsListFragment.newInstance(animalType, savedState);
    }

    public void goToAnimalsList(int position) {
        AnimalsListFragment fragment = animalsListFragments.get(position);
        openFragment(fragment, false, getTagByTabPosition(position));
    }

    public void goToAnimalsDetails(Animal animal) {
        openFragment(AnimalDetailsFragment.newInstance(animal), true, Constants.DETAILS);
    }

    private void openFragment(Fragment fragment, boolean addToBackStack, String tag) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, tag);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    private void openCurrentTab() {
        goToAnimalsList(currentTab);
        tabs.getTabAt(currentTab).select();
    }

    private String getTagByTabPosition(int position) {
        return position == 0 ? Constants.CAT : Constants.DOG;
    }

    public void selectTab(String animalType) {
        if (animalType.equals(Constants.DETAILS)) {
            currentTab = DETAILS_TAB;
            tabs.setVisibility(View.GONE);
            return;
        }
        tabs.setVisibility(View.VISIBLE);
        currentTab = animalType.equals(Constants.CAT) ? 0 : 1;
        tabs.getTabAt(currentTab).select();
    }
}
