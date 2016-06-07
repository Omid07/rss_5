package com.example.omid.rss_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omid.rss_5.util.CategoryNews;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mDescription;
    private TextView mPubDate;
    private ImageView mImage;
    private TextView mLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        CategoryNews categoryNews = (CategoryNews) getIntent().getSerializableExtra(getString(R.string.category_news));
        mTitle = (TextView) findViewById(R.id.text_title);
        mAuthor = (TextView) findViewById(R.id.text_author);
        mDescription = (TextView) findViewById(R.id.text_description);
        mPubDate = (TextView) findViewById(R.id.text_pub_date);
        mImage = (ImageView) findViewById(R.id.image_news);
        mLink = (TextView) findViewById(R.id.link);
        mTitle.setText(categoryNews.getTitle());
        mAuthor.setText(categoryNews.getAuthor());
        mDescription.setText(categoryNews.getDescription());
        mLink.setText(categoryNews.getLink());
        mPubDate.setText(categoryNews.getPubDate());
        Picasso.with(this)
        .load(categoryNews.getImage())
        .into(mImage);
    }
}
