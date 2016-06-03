package com.example.omid.rss_5.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omid.rss_5.util.CategoryNews;
import com.example.omid.rss_5.ui.CategoryNewsViewHolder;
import com.example.omid.rss_5.R;

import java.util.ArrayList;

/**
 * Created by omid on 6/3/16.
 */
public class CategoryNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<CategoryNews> categoryNewsArrayList;
    public CategoryNewsAdapter(ArrayList<CategoryNews> categoryNewsList) {
        this.categoryNewsArrayList = categoryNewsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_news_list, parent, false);
        RecyclerView.ViewHolder viewHolder = new CategoryNewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryNewsViewHolder viewHolder = (CategoryNewsViewHolder) holder;
        viewHolder.update(categoryNewsArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryNewsArrayList.size();
    }
}
