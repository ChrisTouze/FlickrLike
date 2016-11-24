package com.example.chris.flickr;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Chris on 24/11/2016.
 */

public class FlickrListAdapter extends BaseAdapter {
    Context context;
    List<Picture> list;

    public FlickrListAdapter(Context context, List list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final int newI = i;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(context)
                    .inflate(R.layout.flickr_list_embed, viewGroup, false);
        }
        TextView titleTextView = (TextView) convertView.findViewById(R.id.flickr_list_title);
        TextView urlTextView = (TextView) convertView.findViewById(R.id.flickr_list_url);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.test);
        FloatingActionButton fab = (FloatingActionButton) convertView.findViewById(R.id.fab);
        titleTextView.setText(list.get(i).getTitle());

        String urlText;
        if (list.get(i).getUrl().length()>50) {
            urlText=list.get(i).getUrl().substring(0,47)+"...";
        } else {
            urlText=list.get(i).getUrl();
        }
        urlTextView.setText(urlText);
        fab.setFocusable(false);
        fab.setFocusableInTouchMode(false);


        Picasso.with(context).load(list.get(i).getUrl()).fit().centerCrop().into(imageView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(newI);
                notifyDataSetChanged();

            }
        });


        return convertView;
    }
}