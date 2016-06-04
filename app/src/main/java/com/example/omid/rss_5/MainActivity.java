package com.example.omid.rss_5;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.omid.rss_5.adapter.CategoryAdapter;
import com.example.omid.rss_5.ui.CategoryViewHolder;

public class MainActivity extends AppCompatActivity implements  CategoryViewHolder.ClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        intent.putExtra(getString(R.string.position), position);
        startActivity(intent);
    }
}