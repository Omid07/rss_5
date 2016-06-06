package com.example.omid.rss_5.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.omid.rss_5.NewsActivity;
import com.example.omid.rss_5.R;
import com.example.omid.rss_5.util.CategoryNews;
/**
 * Created by omid on 6/3/16.
 */
public class CategoryNewsViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener  {
    private TextView mTextTitle;
    private TextView mTextShortDescription;
    private TextView mTextDate;
    private ClickListener mClickListener;
    private Context mContext;

    public CategoryNewsViewHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        mTextTitle = (TextView) itemView.findViewById(R.id.text_title);
        mTextShortDescription = (TextView) itemView.findViewById(R.id.text_short_description);
        mTextDate = (TextView) itemView.findViewById(R.id.text_date);
        mClickListener = (NewsActivity)context;
        itemView.setOnClickListener(this);
    }

    public void update(CategoryNews categoryNews) {
        mTextTitle.setText(categoryNews.getTitle());
        mTextShortDescription.setText(categoryNews.getDescription());
        mTextDate.setText(categoryNews.getPubDate());
    }

    @Override
    public void onClick(View v) {
        mClickListener.itemClicked(v, getAdapterPosition());
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
