package com.example.omid.rss_5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.omid.rss_5.adapter.CategoryNewsAdapter;
import com.example.omid.rss_5.ui.CategoryNewsViewHolder;
import com.example.omid.rss_5.util.CategoryNews;
import com.example.omid.rss_5.util.Constant;
import com.example.omid.rss_5.util.HttpAsyncCategoryShow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewsActivity extends AppCompatActivity implements HttpAsyncCategoryShow.OnParseCategoryListener, CategoryNewsViewHolder.ClickListener {
    private String[] mCategoryLink;
    private String mUrl;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CategoryNews> mCategoryNewsesList;
    private DatabaseHelper mDatabaseHelper;
    private ArrayList<CategoryNews> mNewsItemShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mDatabaseHelper = new DatabaseHelper(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.category_news_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        int position = getIntent().getExtras().getInt(getString(R.string.position));
        Resources resources = getResources();
        mCategoryLink = resources.getStringArray(R.array.categories_link);
        String[] categoryList = resources.getStringArray(R.array.categories);
        String categoryName = categoryList[position];
        mUrl = mCategoryLink[position];
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMATE);
        String currentDate = dateFormat.format(calendar.getTime());
        SharedPreferences prefs = getSharedPreferences(Constant.DATE, MODE_PRIVATE);
        String storedDate = prefs.getString(Constant.DATE, null);
        if (currentDate.equals(storedDate)) {
            ArrayList<CategoryNews> list = new ArrayList<>();
            list = mDatabaseHelper.checkData(categoryName);
            if (list.size() > 0) {
                showData(list);
            } else {
                new HttpAsyncCategoryShow(NewsActivity.this, mUrl, categoryName).execute();
            }
        } else {
            SharedPreferences.Editor editor = getSharedPreferences(Constant.DATE, MODE_PRIVATE).edit();
            editor.putString(Constant.DATE, dateFormat.format(calendar.getTime()));
            editor.commit();
            new HttpAsyncCategoryShow(NewsActivity.this, mUrl, categoryName).execute();
        }
    }

    public void showData(ArrayList<CategoryNews> categoryNewsList) {
        mCategoryNewsesList = new ArrayList<>();
        mNewsItemShow =categoryNewsList;
        for (int i = 0; i < Constant.LOAD_ITEM && i < categoryNewsList.size(); i++) {
            mCategoryNewsesList.add(categoryNewsList.get(i));
        }
        mAdapter = new CategoryNewsAdapter(mCategoryNewsesList);
        mRecyclerView.setAdapter(mAdapter);
        paging();
    }

    @Override
    public void setData(ArrayList<CategoryNews> categoryNewsList) {
        mCategoryNewsesList = new ArrayList<>();
        mNewsItemShow =categoryNewsList;
        for (int i = 0; i < Constant.LOAD_ITEM; i++) {
            mCategoryNewsesList.add(i, categoryNewsList.get(i));
        }
        mDatabaseHelper.insertData(categoryNewsList);
        mAdapter = new CategoryNewsAdapter(mCategoryNewsesList);
        mRecyclerView.setAdapter(mAdapter);
        paging();
    }

    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
        CategoryNews categoryNews = mCategoryNewsesList.get(position);
        intent.putExtra(getString(R.string.category_news), categoryNews);
        startActivity(intent);
    }

    public void paging(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int count = recyclerView.getChildCount();
                View firstVisibleChild = recyclerView.getChildAt(0);
                int first = recyclerView.getChildAdapterPosition(firstVisibleChild);
                if (newState == 0 && (first + count >= Constant.LOAD_ITEM) ) {
                    int position=mAdapter.getItemCount();
                    for (int i = position; i < position + Constant.LOAD_ITEM && i < mNewsItemShow.size(); i++) {
                        mCategoryNewsesList.add(mNewsItemShow.get(i));
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
