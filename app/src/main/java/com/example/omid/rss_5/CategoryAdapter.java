package com.example.omid.rss_5;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by omid on 5/31/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] mDataset;

    public CategoryAdapter(String[] categoriesDataset) {
        mDataset = categoriesDataset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, parent, false);
        RecyclerView.ViewHolder viewHolder = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
         String category=mDataset[position];
         viewHolder.update(category);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
