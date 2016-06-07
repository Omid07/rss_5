package com.example.omid.rss_5.util;

import java.io.Serializable;

/**
 * Created by omid on 6/3/16.
 */
public class Category {
    private String mTitle;
    private String mDescription;
    private String mDate;
    private String mName;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
