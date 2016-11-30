package com.example.chris.flickr;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chris on 25/11/2016.
 */

public class FlickrService extends Service {
    public static final String HTTPS_WWW_FLICKR_COM = "https://www.flickr.com/";

    private final IBinder binder = new ServiceBinder();
    private FlickrInterface service;
    private FlickrResponseListener flickrResponseListener;


    public void setFlickrResponseListener(FlickrResponseListener flickrResponseListener) {
        this.flickrResponseListener = flickrResponseListener;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPS_WWW_FLICKR_COM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FlickrInterface.class);

        return binder;
    }
    public class ServiceBinder extends Binder {
        FlickrService getService() {
            return FlickrService.this;
        }
    }

    public void getPhotos(String query,String nbResults){
        Call<FlickrResponseDto> flickrPhotosResponseCall =
                service.toto(query,nbResults,getResources().getString(R.string.flickr_api_key));

        flickrPhotosResponseCall.enqueue(new Callback<FlickrResponseDto>() {
            @Override
            public void onResponse(Call<FlickrResponseDto> call,
                                   Response<FlickrResponseDto> response) {


                List<Picture> pictureList=Convert.convert(response.body());
                flickrResponseListener.onPhotosReceived(pictureList);



                 }
                @Override
                public void onFailure(Call<FlickrResponseDto> call,
                        Throwable t) { // Do Something
                    Log.e("TEST","KO");
                     }
                });

    }

}
