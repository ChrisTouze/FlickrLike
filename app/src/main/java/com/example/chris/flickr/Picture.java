package com.example.chris.flickr;

import java.io.Serializable;

/**
 * Created by Chris on 24/11/2016.
 */

public class Picture implements Serializable {
    private String url;
    private String title;

    public Picture(String title, String url) {
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
