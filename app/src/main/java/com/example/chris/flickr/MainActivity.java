package com.example.chris.flickr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Picture> listPicture = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        generateList();

        final ListView listView = (ListView) findViewById(R.id.flickr_list);

        FlickrListAdapter adapter = new FlickrListAdapter(this, listPicture);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, FlickrDetailsActivity.class);
                intent.putExtra("picture", listPicture.get(i));

                startActivity(intent);

            }
        });
    }

    public void generateList(){
        listPicture.add(new Picture("Chaton","https://leschatsdeoceane.files.wordpress.com/2012/09/3392-petit-chaton-mignon-wallfizz2.jpg"));
        listPicture.add(new Picture("Autre chat","https://i.ytimg.com/vi/VwNzpAFT8t8/hqdefault.jpg"));
        listPicture.add(new Picture("Titre 3","https://leschatsdeoceane.files.wordpress.com/2012/09/3392-petit-chaton-mignon-wallfizz2.jpg"));
        listPicture.add(new Picture("Titre 4","https://i.ytimg.com/vi/VwNzpAFT8t8/hqdefault.jpg"));
        listPicture.add(new Picture("Titre 5","https://i.ytimg.com/vi/VwNzpAFT8t8/hqdefault.jpg"));
        listPicture.add(new Picture("Titre 6","https://i.ytimg.com/vi/VwNzpAFT8t8/hqdefault.jpg"));
    }
}