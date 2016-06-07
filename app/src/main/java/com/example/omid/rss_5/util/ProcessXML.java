package com.example.omid.rss_5.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by omid on 6/2/16.
 */
public class ProcessXML {
    private InputStream mInputStream;
    private CategoryNews mCategory;
    private ArrayList<CategoryNews> mCategoryNewsList;

    public ProcessXML(InputStream inputStream) {
        this.mInputStream = inputStream;
    }

    public ArrayList<CategoryNews> getData() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            Document xmlDocument = null;
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            xmlDocument = documentBuilder.parse(mInputStream);
            Element rootElement = xmlDocument.getDocumentElement();
            mCategoryNewsList = new ArrayList<>();
            NodeList items = rootElement.getElementsByTagName("item");
            NodeList itemChildren = null;
            Node currentChild = null;
            Node curremtItem = null;
            for (int i = 0; i < items.getLength(); i++) {
                curremtItem = items.item(i);
                itemChildren = curremtItem.getChildNodes();
                mCategory = new CategoryNews();
                for (int j = 0; j < itemChildren.getLength(); j++) {
                    currentChild = itemChildren.item(j);
                    String name = currentChild.getNodeName();
                    switch (name) {
                        case Constant.TITLE:
                            String title = currentChild.getTextContent();
                            mCategory.setTitle(title);
                            break;
                        case Constant.PUBDATE:
                            String pubDate = currentChild.getTextContent();
                            mCategory.setPubDate(pubDate);
                            break;
                        case Constant.AUTHOR:
                            String author = currentChild.getTextContent();
                            mCategory.setAuthor(author);
                            break;
                        case Constant.DESCRIPTION:
                            String description = currentChild.getTextContent();
                            mCategory.setDescription(description);
                            break;
                        case Constant.IMAGE:
                            Element element = (Element) currentChild;
                            NamedNodeMap attributes = element.getAttributes();
                            Node enclosureItem = attributes.getNamedItem("url");
                            String image = enclosureItem.getNodeValue();
                            mCategory.setImage(image);
                            break;
                        case Constant.LINK:
                            String link = currentChild.getTextContent();
                            mCategory.setLink(link);
                            break;
                        default:
                            break;
                    }
                }
                mCategoryNewsList.add(mCategory);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return mCategoryNewsList;
    }
}
