package com.example.chris.flickr;

import java.util.List;

/**
 * Created by Chris on 28/11/2016.
 */

public interface FlickrResponseListener {
   void onPhotosReceived(List<Picture> pictureList);
}
