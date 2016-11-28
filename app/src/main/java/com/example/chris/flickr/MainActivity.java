package com.example.chris.flickr;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Picture> listPicture = new ArrayList<>();
    private FlickrService flickrService;
    boolean bound = false;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, FlickrService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }
    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);


        Button launchButton = (Button)findViewById(R.id.flickr_list_button);
        final EditText query = (EditText) findViewById(R.id.flickr_list_input);

        final ListView listView = (ListView) findViewById(R.id.flickr_list);

        final FlickrListAdapter adapter = new FlickrListAdapter(this, listPicture);

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

        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 InputMethodManager manager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                if (bound) {
                    adapter.setList(flickrService.generateList());
                    adapter.notifyDataSetChanged();
                    String queryMessage = "Votre recherche : "+ query.getText().toString();
                    Toast.makeText(MainActivity.this,queryMessage,Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            FlickrService.ServiceBinder binder = (FlickrService.ServiceBinder) service;
            flickrService = binder.getService();
            bound = true;

        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

 }