package com.example.chris.flickr;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Chris on 28/11/2016.
 */

public interface FlickrInterface {
    @GET("services/rest/?method=flickr.photos.search&safe_search=1&format=json&nojsoncallback=1")
    Call<FlickrResponseDto> toto(@Query("tags") String query,@Query("per_page") String nbResults,@Query("api_key") String key);

}
