package com.example.chris.flickr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FlickrDetailsActivity extends AppCompatActivity {

    Picture picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_details);

        TextView titleTextView=(TextView)findViewById(R.id.flickr_details_title);
        TextView urlTextView = (TextView)findViewById(R.id.flickr_details_url);
        ImageView pictureView = (ImageView)findViewById(R.id.flickr_details_picture);

        picture = (Picture) getIntent().getSerializableExtra("picture");
        titleTextView.setText(picture.getTitle());
        urlTextView.setText(picture.getUrl());
        Picasso.with(this).load(picture.getUrl()).fit().centerCrop().into(pictureView);
    }
}
