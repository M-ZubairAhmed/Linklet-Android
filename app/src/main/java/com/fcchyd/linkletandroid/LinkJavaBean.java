package com.fcchyd.linkletandroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class LinkJavaBean {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("__v")
    @Expose
    private long v;

    @SerializedName("author")
    @Expose
    private Object author;

    @SerializedName("date")
    @Expose
    private Object date;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("timestamp")
    @Expose
    private long timestamp;

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    long getV() {
        return v;
    }

    void setV(long v) {
        this.v = v;
    }

    Object getAuthor() {
        return author;
    }

    void setAuthor(Object author) {
        this.author = author;
    }

    Object getDate() {
        return date;
    }

    void setDate(Object date) {
        this.date = date;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getImage() {
        return image;
    }

    void setImage(String image) {
        this.image = image;
    }

    String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getUrl() {
        return url;
    }

    void setUrl(String url) {
        this.url = url;
    }

    long getTimestamp() {
        return timestamp;
    }

    void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}