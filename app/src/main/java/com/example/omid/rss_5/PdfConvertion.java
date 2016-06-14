package com.example.omid.rss_5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.omid.rss_5.util.Constant;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by omid on 6/13/16.
 */
public class PdfConvertion {
    public static File convertToPdf(String title, String author, Image image, String description, String pubDate) {
        Document document = new Document();
        File directory = new File(Constant.CREATE_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, title);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            try {
                PdfWriter.getInstance(document, fOut);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            document.open();
            document.add(new Paragraph(title));
            document.add(new Paragraph(author));
            if (image != null) {
                document.add(image);
            }
            document.add(new Paragraph(description));
            document.add(new Paragraph(pubDate));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
            return file;
        }
    }
}
