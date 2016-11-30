package com.example.chris.flickr;
import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Chris on 30/11/2016.
 */
@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION)

public class AppDataBase {
    public static final String NAME = "Flickr";
    public static final int VERSION = 2;
}


