package com.example.chris.flickr;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FlickrResponseListener,AdapterView.OnItemSelectedListener {

    public static final String ACTIVITY_VIEW_TYPE = "activityViewType";
    public static final String FLICKR_SETTINGS = "FLICKR_SETTINGS";
    private FlickrService flickrService;
    boolean bound = false;
    FlickrListAdapter adapter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private PictureDbManager pictureDbManager;

    private SharedPreferences settings;
    String nbResultsByPage;




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

        settings = getSharedPreferences(FLICKR_SETTINGS,MODE_PRIVATE);
        nbResultsByPage = settings.getString("nbResultsByPage", "5");
        final String activityType = settings.getString(ACTIVITY_VIEW_TYPE,"s");

        pictureDbManager = new PictureDbManager(this);

        final LinearLayout searchLayout = (LinearLayout)findViewById(R.id.search_zone);
        final EditText editText = (EditText) findViewById(R.id.flickr_list_input);
        final ListView listView = (ListView) findViewById(R.id.flickr_list);
        Button drawerButtonSearch = (Button)findViewById(R.id.drawer_search_button);
        Button drawerButtonHist = (Button)findViewById(R.id.drawer_historic_button);
        drawerInit();

        spinnerInit();





        // Drawer search button :
        drawerButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchLayout.setVisibility(View.VISIBLE);
                SharedPreferences settings = getSharedPreferences(FLICKR_SETTINGS,MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(ACTIVITY_VIEW_TYPE, "s");
                editor.commit();

             }
        });

        drawerButtonHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchLayout.setVisibility(View.GONE);
                SharedPreferences settings = getSharedPreferences(FLICKR_SETTINGS,MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(ACTIVITY_VIEW_TYPE, "h");
                editor.commit();
                if (adapter!=null){
                    adapter.setList(pictureDbManager.getAll());
                    adapter.notifyDataSetChanged();
                }

            }
        });



        Button launchButton = (Button)findViewById(R.id.flickr_list_button);
        launchButton.requestFocus();

     /*   if(getCurrentFocus()!=null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }*/

        adapter = new FlickrListAdapter(this);

        listView.setAdapter(adapter);

        if (activityType.equals("s")) {
            drawerButtonSearch.performClick();
        } else {
            drawerButtonHist.performClick();
            if (adapter!=null){
                adapter.setList(pictureDbManager.getAll());
            }
        }


        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pictureDbManager.save(adapter.getItem(i));
                Intent intent = new Intent(MainActivity.this, FlickrDetailsActivity.class);
                intent.putExtra("picture", adapter.getItem(i));

                startActivity(intent);
            }
        });

        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (bound) {

                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    String myQuery = editText.getText().toString();
                    myQuery.trim();
                    myQuery.replace(' ','+');
                    flickrService.getPhotos(myQuery,nbResultsByPage);
                    String queryMessage = "Votre recherche : "+ editText.getText().toString();
                    Toast.makeText(MainActivity.this,queryMessage,Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void drawerInit() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */

              /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("closed");
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("opened");
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void spinnerInit() {
        Spinner spinner = (Spinner) findViewById(R.id.nb_results);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.nbresults_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(spinnerAdapter.getPosition(nbResultsByPage));
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            FlickrService.ServiceBinder binder = (FlickrService.ServiceBinder) service;
            flickrService = binder.getService();
            flickrService.setFlickrResponseListener(MainActivity.this);
            bound = true;

        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    @Override
    public void onPhotosReceived(List<Picture> pictureList) {
        adapter.setList(pictureList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String nbResults = adapterView.getItemAtPosition(i).toString();

        SharedPreferences settings = getSharedPreferences(FLICKR_SETTINGS,MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("nbResultsByPage", nbResults);
        editor.commit();
         nbResultsByPage=nbResults;


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.e("TEST","nothing");
    }
}