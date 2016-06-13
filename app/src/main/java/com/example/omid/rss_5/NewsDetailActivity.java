package com.example.omid.rss_5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omid.rss_5.util.CategoryNews;
import com.example.omid.rss_5.util.Constant;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class NewsDetailActivity extends AppCompatActivity {
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mDescription;
    private TextView mPubDate;
    private ImageView mImage;
    private TextView mLink;
    private Button mShare, mPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        final CategoryNews categoryNews = (CategoryNews) getIntent().getSerializableExtra(getString(R.string.category_news));
        mShare = (Button) findViewById(R.id.btn_share);
        mPdf = (Button) findViewById(R.id.btn_pdf);
        mTitle = (TextView) findViewById(R.id.text_title);
        mAuthor = (TextView) findViewById(R.id.text_author);
        mDescription = (TextView) findViewById(R.id.text_description);
        mPubDate = (TextView) findViewById(R.id.text_pub_date);
        mImage = (ImageView) findViewById(R.id.image_news);
        mLink = (TextView) findViewById(R.id.text_link);
        mTitle.setText(categoryNews.getTitle());
        mAuthor.setText(categoryNews.getAuthor());
        mDescription.setText(categoryNews.getDescription());
        mLink.setText(categoryNews.getLink());
        mPubDate.setText(categoryNews.getPubDate());
        Picasso.with(this)
        .load(categoryNews.getImage())
        .into(mImage);
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType(Constant.SET_TYPE);
                shareIntent.putExtra(Intent.EXTRA_TEXT, categoryNews.getLink());
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
            }
        });
        mPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString();
                String author = mAuthor.getText().toString();
                String imageUrl = categoryNews.getImage();
                Image image = null;
                try {
                    image = Image.getInstance(new URL(imageUrl));
                } catch (BadElementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String description = mDescription.getText().toString();
                String pubDate = mPubDate.getText().toString();
                File file = PdfConvertion.convertToPdf(title, author, image, description, pubDate);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), Constant.APP_TYPE);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }
}
