package com.example.omid.rss_5;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.omid.rss_5.adapter.CategoryNewsAdapter;
import com.example.omid.rss_5.util.CategoryNews;
import com.example.omid.rss_5.util.HttpAsyncCategoryShow;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements HttpAsyncCategoryShow.OnParseCategoryListener{
    private String[] mCategoryLink;
    private String mUrl;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        mAdapter = new CategoryNewsAdapter(categoryNewsList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
