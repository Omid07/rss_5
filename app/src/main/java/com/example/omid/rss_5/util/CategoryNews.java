package com.example.omid.rss_5.util;

import android.util.Log;

/**
 * Created by omid on 6/3/16.
 */
public class CategoryNews {
    private String pubDate;
    private String title;
    private String author;
    private String description;

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
