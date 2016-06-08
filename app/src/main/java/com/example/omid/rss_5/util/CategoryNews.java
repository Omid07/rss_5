package com.example.omid.rss_5.util;
import java.io.Serializable;
/**
 * Created by omid on 6/3/16.
 */
public class CategoryNews implements Serializable {
    private String mPubDate;
    private String mTitle;
    private String mAuthor;
    private String mDescription;
    private String mImage;
    private String mLink;
    private String mCategoryName;

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String name) {
        mCategoryName = name;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String pubDate) {
        mPubDate = pubDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
