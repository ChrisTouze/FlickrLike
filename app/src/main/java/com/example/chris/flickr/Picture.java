package com.example.chris.flickr;

import java.io.Serializable;

/**
 * Created by Chris on 24/11/2016.
 */

public class Picture implements Serializable {
    private String url;
    private String title;
    private String thumbUrl;

    public Picture() {
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (url != null ? !url.equals(picture.url) : picture.url != null) return false;
        if (title != null ? !title.equals(picture.title) : picture.title != null) return false;
        return thumbUrl != null ? thumbUrl.equals(picture.thumbUrl) : picture.thumbUrl == null;

    }

    public Picture(String title, String url, String thumbUrl) {
        this.url = url;
        this.title = title;
        this.thumbUrl = thumbUrl;
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

    public Picture(String title,String url) {
        this.url = url;
        this.title = title;
    }
}
