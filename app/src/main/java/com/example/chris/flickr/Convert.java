package com.example.chris.flickr;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 25/11/2016.
 */

public class Convert {

    public static List convert(FlickrResponseDto flickrResponseDto){
        List<Picture> pictureList = new ArrayList<>();

        if (flickrResponseDto.getStat().equals("ok")){
            for (PhotoDto p:flickrResponseDto.getPhotos().getPhoto()) {
                pictureList.add(new Picture(p.getTitle(),generateUrl(p),generateThumbUrl(p)));

            }
        }
        return pictureList;
    }

    private static String generateUrl(PhotoDto p){
        return "https://farm"+p.getFarm()+".static.flickr.com/"+p.getServer()+"/"+p.getId()+"_"+p.getSecret()+".jpg";

    }
    private static String generateThumbUrl(PhotoDto p){
        return "https://farm"+p.getFarm()+".static.flickr.com/"+p.getServer()+"/"+p.getId()+"_"+p.getSecret()+"_s.jpg";

    }

}
