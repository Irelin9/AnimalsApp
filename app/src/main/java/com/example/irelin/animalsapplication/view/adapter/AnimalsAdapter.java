package com.example.irelin.animalsapplication.view.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irelin.animalsapplication.R;
import com.example.irelin.animalsapplication.custom.ImageLoader;
import com.example.irelin.animalsapplication.data.models.Animal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener;
    private List<Animal> items;

    public AnimalsAdapter(List<Animal> items, OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animals_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView image;
        @BindView(R.id.item_text)
        TextView text;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Animal animal) {
            text.setText(animal.getTitle());
            ImageLoader.load(image, animal.getUrl(), itemView.getContext());
            itemView.setOnClickListener(view -> itemClickListener.onClick(animal));
        }
    }

    public interface OnItemClickListener {
        void onClick(Animal animal);
    }
}
