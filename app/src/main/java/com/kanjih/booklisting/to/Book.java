package com.kanjih.booklisting.to;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by kneto on 2/19/17.
 */

public class Book {
    private String id;
    private String title;
    private List<String> authors;
    private String language;
    private Bitmap smallThumbnail;

    public Book(String id, String title, List<String> authors, Bitmap smallThumbnail) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.smallThumbnail = smallThumbnail;
    }

    public Book(String id, String title, List<String> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Bitmap getSmallThumbnail() {
        return smallThumbnail;
    }
}
