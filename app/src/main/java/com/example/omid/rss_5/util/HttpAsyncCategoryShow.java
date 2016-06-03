package com.example.omid.rss_5.util;

import android.os.AsyncTask;

import com.example.omid.rss_5.NewsActivity;
import com.example.omid.rss_5.util.CategoryNews;
import com.example.omid.rss_5.util.Constant;
import com.example.omid.rss_5.util.ProcessXML;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by omid on 6/2/16.
 */
public class HttpAsyncCategoryShow extends AsyncTask<Void, Void, ArrayList<CategoryNews>> {
    private NewsActivity mData;
    private String mCategoryUrl;
    private ArrayList<CategoryNews> mCategoryNewsList;

    public HttpAsyncCategoryShow(NewsActivity data, String categoryUrl) {
        mData = data;
        mCategoryUrl = categoryUrl;
    }

    @Override
    protected ArrayList<CategoryNews> doInBackground(Void... params) {
        URL url = null;
        try {
            url = new URL(mCategoryUrl);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(Constant.GET);
            InputStream inputStream = null;
            inputStream = connection.getInputStream();
            ProcessXML processXML = new ProcessXML(inputStream);
            mCategoryNewsList = processXML.getData();
            return mCategoryNewsList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mCategoryNewsList;
    }

    protected void onPostExecute(ArrayList<CategoryNews> list) {
        super.onPostExecute(list);
        mData.setData(list);
    }

    public interface OnParseCategoryListener {
        void setData(ArrayList<CategoryNews> list);
    }
}

