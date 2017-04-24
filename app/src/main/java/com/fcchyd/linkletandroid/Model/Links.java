
package com.fcchyd.linkletandroid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Links {

    @SerializedName("page")
    @Expose
    private String page;
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
    private List<Link> links = null;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public long getPerPage() {
        return perPage;
    }

    public void setPerPage(long perPage) {
        this.perPage = perPage;
    }

    public long getTotalLinks() {
        return totalLinks;
    }

    public void setTotalLinks(long totalLinks) {
        this.totalLinks = totalLinks;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
