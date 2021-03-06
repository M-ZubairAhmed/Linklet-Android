package com.fcchyd.linkletandroid.Type;

public class LinkDataType {

    private String title;
    private String description;
    private String imageUrl;
    private String url;

    public LinkDataType(String title, String description, String imageUrl, String url) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public LinkDataType(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public LinkDataType(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }
}
