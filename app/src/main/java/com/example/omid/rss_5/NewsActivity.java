package com.example.omid.rss_5;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.omid.rss_5.adapter.CategoryNewsAdapter;
import com.example.omid.rss_5.ui.CategoryNewsViewHolder;
import com.example.omid.rss_5.util.CategoryNews;
import com.example.omid.rss_5.util.HttpAsyncCategoryShow;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements HttpAsyncCategoryShow.OnParseCategoryListener, CategoryNewsViewHolder.ClickListener{
    private String[] mCategoryLink;
    private String mUrl;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CategoryNews> mCategoryNewsesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mRecyclerView = (RecyclerView) findViewById(R.id.category_news_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        int position = getIntent().getExtras().getInt(getString(R.string.position));
        Resources resources = getResources();
        mCategoryLink = resources.getStringArray(R.array.categories_link);
        mUrl = mCategoryLink[position];
        new HttpAsyncCategoryShow(NewsActivity.this, mUrl).execute();
    }

    @Override
    public void setData(ArrayList<CategoryNews> categoryNewsList) {
        mCategoryNewsesList = new ArrayList<>();
        for(int i=0;i<categoryNewsList.size();i++) {
            mCategoryNewsesList.add(i,categoryNewsList.get(i));
        }
        mAdapter = new CategoryNewsAdapter(categoryNewsList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
        CategoryNews categoryNews = mCategoryNewsesList.get(position);
        intent.putExtra(getString(R.string.category_news), categoryNews);
        startActivity(intent);
    }
}
