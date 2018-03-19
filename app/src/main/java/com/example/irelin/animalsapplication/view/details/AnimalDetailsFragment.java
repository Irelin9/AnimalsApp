package com.example.irelin.animalsapplication.view.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irelin.animalsapplication.MainActivity;
import com.example.irelin.animalsapplication.R;
import com.example.irelin.animalsapplication.custom.ImageLoader;
import com.example.irelin.animalsapplication.data.models.Animal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalDetailsFragment extends Fragment {
    private static final String ANIMAL = "ANIMAL";

    private Animal animal;

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.text)
    TextView text;

    public AnimalDetailsFragment() {
    }

    public static AnimalDetailsFragment newInstance(Animal animal) {
        AnimalDetailsFragment fragment = new AnimalDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ANIMAL, animal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            animal = (Animal) getArguments().getSerializable(ANIMAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_details, container, false);
        ButterKnife.bind(this, view);
        text.setText(animal.getTitle());
        ImageLoader.load(image, animal.getUrl(), getContext());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setTabsVisibility(false);
    }
}
