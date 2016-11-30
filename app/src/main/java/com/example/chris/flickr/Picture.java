package com.example.chris.flickr;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ColumnIgnore;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by Chris on 24/11/2016.
 */

@Table(database = AppDataBase.class)
public class Picture extends BaseModel implements Serializable {

    @Column
    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String url;
    @Column
    private String title;
   @ColumnIgnore
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
