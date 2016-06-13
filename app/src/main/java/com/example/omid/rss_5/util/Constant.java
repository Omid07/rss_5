package com.example.omid.rss_5.util;

import android.os.Environment;

/**
 * Created by omid on 6/2/16.
 */
public class Constant {
    public static final String GET = "GET";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";
    public static final String PUBDATE = "pubDate";
    public static final String AUTHOR = "author";
    public static final String IMAGE = "enclosure";
    public static final String LINK = "link";
    public static final String DATE_FORMATE = "dd-MMM-yyyy";
    public static final String SET_TYPE = "text/plain";
    public static final int LOAD_ITEM = 20;
    public static final String CREATE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RSS_Text";
    public static final String NO_CONNECTION = "Not Connected to Internet";
    public static final String APP_TYPE = "application/pdf";
}
