package com.example.chris.flickr;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 25/11/2016.
 */

public class FlickrService extends Service {
    private final IBinder binder = new ServiceBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public class ServiceBinder extends Binder {
        FlickrService getService() {
            return FlickrService.this;
        }
    }

    public List<Picture> generateList(){
        List<Picture> listPicture = new ArrayList<>();

        listPicture.add(new Picture("Chaton","https://leschatsdeoceane.files.wordpress.com/2012/09/3392-petit-chaton-mignon-wallfizz2.jpg"));
        listPicture.add(new Picture("Autre chat","https://i.ytimg.com/vi/VwNzpAFT8t8/hqdefault.jpg"));
        listPicture.add(new Picture("Titre 3","https://leschatsdeoceane.files.wordpress.com/2012/09/3392-petit-chaton-mignon-wallfizz2.jpg"));
        listPicture.add(new Picture("Titre 4","https://i.ytimg.com/vi/VwNzpAFT8t8/hqdefault.jpg"));
        listPicture.add(new Picture("Titre 5","https://i.ytimg.com/vi/VwNzpAFT8t8/hqdefault.jpg"));
        listPicture.add(new Picture("Titre 6","https://i.ytimg.com/vi/VwNzpAFT8t8/hqdefault.jpg"));

        return listPicture;
    }

}
