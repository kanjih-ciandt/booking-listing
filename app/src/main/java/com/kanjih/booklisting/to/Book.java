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
    private String publisher;
    private String description;
    private String linkSmallThumbnail;
    private String linkThumbnail;
    private String language;
    private Integer pageCount;
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


    public Book(String id, String title, List<String> authors, String linkSmallThumbnail) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.linkSmallThumbnail = linkSmallThumbnail;
    }

    public Book(String id, String title, List<String> authors, String publisher, String description, String linkSmallThumbnail, String linkThumbnail, String language, Integer pageCount) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.description = description;
        this.linkSmallThumbnail = linkSmallThumbnail;
        this.linkThumbnail = linkThumbnail;
        this.language = language;
        this.pageCount = pageCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkSmallThumbnail() {
        return linkSmallThumbnail;
    }

    public void setLinkSmallThumbnail(String linkSmallThumbnail) {
        this.linkSmallThumbnail = linkSmallThumbnail;
    }

    public String getLinkThumbnail() {
        return linkThumbnail;
    }

    public void setLinkThumbnail(String linkThumbnail) {
        this.linkThumbnail = linkThumbnail;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Bitmap getSmallThumbnail() {
        return smallThumbnail;
    }
}
