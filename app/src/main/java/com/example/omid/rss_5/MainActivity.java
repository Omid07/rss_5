package com.example.omid.rss_5;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.omid.rss_5.adapter.CategoryAdapter;
import com.example.omid.rss_5.ui.CategoryViewHolder;
import com.example.omid.rss_5.util.CategoryNews;
import com.example.omid.rss_5.util.Constant;
import com.example.omid.rss_5.util.HttpAsyncCategoryShow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  CategoryViewHolder.ClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.category_recycler_view);
        Resources resources = getResources();
        String [] categories = resources.getStringArray(R.array.categories);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CategoryAdapter(categories);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void itemClicked(View view, int position) {
        mDatabaseHelper = new DatabaseHelper(this);
        Resources resources = getResources();
        String [] categories = resources.getStringArray(R.array.categories);
        String categoryName = categories[position];
        ArrayList<CategoryNews> list = new ArrayList<>();
        list = mDatabaseHelper.checkData(categoryName);
        if (list.size() > 0) {
            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            intent.putExtra(getString(R.string.position), position);
            startActivity(intent);
        } else {
            if (isNetworkAvailable()) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra(getString(R.string.position), position);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), Constant.NO_CONNECTION, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}