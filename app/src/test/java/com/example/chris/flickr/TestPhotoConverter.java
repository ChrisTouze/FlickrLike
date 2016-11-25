package com.example.chris.flickr;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Chris on 25/11/2016.
 */

public class TestPhotoConverter {

    List<PhotoDto> listPhoto =  new ArrayList<>();

    @Test
    public void test() throws Exception {

        //local
        List<Picture> pictureList = new ArrayList<>();
        pictureList.add(new Picture("IMG_6934","https://farm6.static.flickr.com/5786/30407248084_b81a4dc864.jpg","https://farm6.static.flickr.com/5786/30407248084_b81a4dc864_s.jpg"));
        pictureList.add(new Picture("IMG_6928","https://farm6.static.flickr.com/5547/30407245244_fb7d67938c.jpg","https://farm6.static.flickr.com/5547/30407245244_fb7d67938c_s.jpg"));


        // flickr
        listPhoto.add(new PhotoDto("30407248084","35468139997@N01","b81a4dc864","5786",6,"IMG_6934",1,0,0));
        listPhoto.add(new PhotoDto("30407245244","35468139997@N01","fb7d67938c","5547",6,"IMG_6928",1,0,0));

        FlickrPhotosDto flickrPhotosDto = new FlickrPhotosDto(1,81246,5,"406227",listPhoto);
        FlickrResponseDto flickrResponseDto = new FlickrResponseDto(flickrPhotosDto,"ok");

        assertEquals(pictureList,Convert.convert(flickrResponseDto));


    }

}
