package com.fcchyd.linkletandroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class LinksJavaBean {

    @SerializedName("page")
    @Expose
    private long page;

    @SerializedName("perPage")
    @Expose
    private long perPage;

    @SerializedName("totalLinks")
    @Expose
    private long totalLinks;

    @SerializedName("isLastPage")
    @Expose
    private boolean isLastPage;

    @SerializedName("links")
    @Expose

    private List<LinkJavaBean> links = null;

    long getPage() {
        return page;
    }

    void setPage(long page) {
        this.page = page;
    }

    long getPerPage() {
        return perPage;
    }

    void setPerPage(long perPage) {
        this.perPage = perPage;
    }

    long getTotalLinks() {
        return totalLinks;
    }

    void setTotalLinks(long totalLinks) {
        this.totalLinks = totalLinks;
    }

    boolean isIsLastPage() {
        return isLastPage;
    }

    void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    List<LinkJavaBean> getLinks() {
        return links;
    }

    void setLinks(List<LinkJavaBean> links) {
        this.links = links;
    }

}
