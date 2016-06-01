package com.example.omid.rss_5;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by omid on 5/31/16.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private TextView textCategory;
    private TextView textTitle;
    private TextView textShortDescription;
    private TextView textDate;

    public CategoryViewHolder(View view) {
        super(view);
        textCategory = (TextView) view.findViewById(R.id.text_category);
        textTitle = (TextView) view.findViewById(R.id.text_title);
        textShortDescription = (TextView) view.findViewById(R.id.text_short_description);
        textDate = (TextView) view.findViewById(R.id.text_date);
    }

    public void update(String category){
        textCategory.setText(category);
    }
}
