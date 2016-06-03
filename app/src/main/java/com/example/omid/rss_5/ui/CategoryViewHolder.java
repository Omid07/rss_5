package com.example.omid.rss_5.ui;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.omid.rss_5.MainActivity;
import com.example.omid.rss_5.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView textCategory;
    private Context context;
    private ClickListener clickListener;

    public CategoryViewHolder(View view,Context context) {
        super(view);
        this.context = context;
        textCategory = (TextView) view.findViewById(R.id.text_category);
        clickListener=(MainActivity)context;
        view.setOnClickListener(this);
    }

    public void update(String category){
        textCategory.setText(category);
    }

    @Override
    public void onClick(View v) {
        clickListener.itemClicked(v, getAdapterPosition());
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}