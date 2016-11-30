package com.example.chris.flickr;

import android.content.Context;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Chris on 30/11/2016.
 */

public class PictureDbManager {
    public PictureDbManager(Context context) {
        FlowManager.init(new
                FlowConfig.Builder(context).openDatabasesOnInit(true).build());
    }

    public void save(Picture picture) {
        try {
            picture.save();
        } catch (Exception e) {
            Log.w("ERREUR", e.toString());
        }
    }

    public List<Picture> getAll(){
        return SQLite.select()
                .from(Picture.class).queryList();
    }

    public void delete(Picture picture){
        picture.delete();
    }
}
