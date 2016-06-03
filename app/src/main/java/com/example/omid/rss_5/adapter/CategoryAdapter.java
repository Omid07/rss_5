package com.example.omid.rss_5.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omid.rss_5.ui.CategoryViewHolder;
import com.example.omid.rss_5.R;

/**
 * Created by omid on 5/31/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  String[] mDataset;
    private Context mContext;

    public CategoryAdapter(String[] categoriesDataSet) {
        mDataset = categoriesDataSet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_list, parent, false);
        RecyclerView.ViewHolder viewHolder = new CategoryViewHolder(view, mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        String category = mDataset[position];
        viewHolder.update(category);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}