package com.example.omid.rss_5.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.omid.rss_5.R;
import com.example.omid.rss_5.util.CategoryNews;

/**
 * Created by omid on 6/3/16.
 */
public class CategoryNewsViewHolder  extends RecyclerView.ViewHolder {
    private TextView textTitle;
    private TextView textShortDescription;
    private TextView textDate;
    public CategoryNewsViewHolder(View itemView) {
        super(itemView);
        textTitle = (TextView) itemView.findViewById(R.id.text_title);
        textShortDescription = (TextView) itemView.findViewById(R.id.text_short_description);
        textDate = (TextView) itemView.findViewById(R.id.text_date);
    }

    public void update(CategoryNews categoryNews) {
        textTitle.setText(categoryNews.getTitle());
        textShortDescription.setText(categoryNews.getDescription());
        textDate.setText(categoryNews.getPubDate());
    }
}
