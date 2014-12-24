package com.exadel.belarusattractions.dto.sights;

/**
 * Developer: Paulau Aliaksandr
 * Created: 1:30 PM, 12/11/12
 */

public class Photo {
    private String url;
    private String title;

    public Photo() {
    }

    public Photo(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
