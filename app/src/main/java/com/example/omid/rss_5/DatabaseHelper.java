package com.example.omid.rss_5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.omid.rss_5.util.CategoryNews;

import java.util.ArrayList;

/**
 * Created by omid on 6/6/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rss_5";
    private static final String TABLE_NAME = "category_news_table";
    private static final String ID = "id";
    private static final String CATEGORY_NAME = "category_name";
    private static final String NEWS_TITLE = "title";
    private static final String NEWS_DESCRIPTION = "description";
    private static final String NEWS_PUBDATE = "pub_date";
    private static final String NEWS_AUTHOR = "author";
    private static final String NEWS_LINK = "link";
    private static final String NEWS_IMAGE = "image_url";

    private static final String CREATE_TABLE_RESULT = "CREATE TABLE " + TABLE_NAME
            + "(" + ID + " INTEGER PRIMARY KEY, " + CATEGORY_NAME +
            " text, " + NEWS_TITLE + " text, " + NEWS_DESCRIPTION + " text, " + NEWS_PUBDATE + " text, " + NEWS_AUTHOR + " text, " + NEWS_LINK + " text, " + NEWS_IMAGE + " text )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESULT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(ArrayList<CategoryNews> list) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < list.size(); i++) {
            contentValues.put(CATEGORY_NAME, list.get(i).getCategoryName());
            contentValues.put(NEWS_TITLE, list.get(i).getTitle());
            contentValues.put(NEWS_DESCRIPTION, list.get(i).getDescription());
            contentValues.put(NEWS_PUBDATE, list.get(i).getPubDate());
            contentValues.put(NEWS_AUTHOR, list.get(i).getAuthor());
            contentValues.put(NEWS_LINK, list.get(i).getLink());
            contentValues.put(NEWS_IMAGE, list.get(i).getImage());
            db.insert(TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<CategoryNews> checkData(String name) {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<CategoryNews> categoryNewsList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + CATEGORY_NAME + " = '" + name + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String categoryName = cursor.getString(cursor.getColumnIndex(CATEGORY_NAME));
                String title = cursor.getString(cursor.getColumnIndex(NEWS_TITLE));
                String description = cursor.getString(cursor.getColumnIndex(NEWS_DESCRIPTION));
                String pubDate = cursor.getString(cursor.getColumnIndex(NEWS_PUBDATE));
                String author = cursor.getString(cursor.getColumnIndex(NEWS_AUTHOR));
                String link = cursor.getString(cursor.getColumnIndex(NEWS_LINK));
                String image = cursor.getString(cursor.getColumnIndex(NEWS_IMAGE));
                CategoryNews categoryNews = new CategoryNews();
                categoryNews.setCategoryName(categoryName);
                categoryNews.setTitle(title);
                categoryNews.setDescription(description);
                categoryNews.setPubDate(pubDate);
                categoryNews.setAuthor(author);
                categoryNews.setLink(link);
                categoryNews.setImage(image);
                categoryNewsList.add(categoryNews);
                cursor.moveToNext();
            }
        }
        return categoryNewsList;
    }
}

