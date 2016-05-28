package com.example.fabrice.a9reddit;

import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fabrice.a9reddit.interfaces.ListingsInterface;
import com.example.fabrice.a9reddit.models.Listing;
import com.example.fabrice.a9reddit.models.ListingData;
import com.example.fabrice.a9reddit.models.ListingElement;
import com.example.fabrice.a9reddit.persistence.Constants;
import com.example.fabrice.a9reddit.persistence.RedditDB;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ListingsFragment.OnFragmentInteractionListener {

    ListingsInterface redditRestApi;
    private RedditDB redditDB;
    Toolbar toolbar;
    String subReddit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        this.redditDB = new RedditDB(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            getListings();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();

        redditRestApi = restAdapter.create(ListingsInterface.class);

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id) {
            case R.id.subreddit_askReddit: {
                subReddit = "askreddit";
                break;
            }
            case R.id.subreddit_funny: {
                subReddit = "funny";
                break;
            }
            case R.id.subreddit_gaming: {
                subReddit = "gaming";
                break;
            }
            case R.id.subreddit_pics: {
                subReddit = "pics";
                break;
            }
            case R.id.subreddit_til: {
                subReddit = "til";
                break;
            }
            case R.id.subreddit_worldnews: {
                subReddit = "worldnews";
                break;
            }
        }
        getListings();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getListings(){
        redditRestApi.getListing("r", subReddit + ".json", new Callback<Listing>() {
            @Override
            public void success(Listing listing, Response response) {
                persistSubredditData(listing);
                notifySubRedditsFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new ListingsFragment().newInstance());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void persistSubredditData(Listing listing){
        this.redditDB.open();
        this.redditDB.deleteAllElements();

        List<ListingElement> listingElements = listing.getData().getChildren();

        for (ListingElement element: listingElements){
            ContentValues values = new ContentValues();
            values.put(Constants.THUMBNAIL, element.getListingData().getThumbnail());
            values.put(Constants.LISTING_NAME, element.getListingData().getTitle());
            values.put(Constants.URL, element.getListingData().getUrl());
            this.redditDB.insertSubreddit(values);
        }
        this.redditDB.close();
    }

    private void notifySubRedditsFragment() {
        Intent intent = new Intent();
        intent.setAction(ListingsFragment.ACTION_UPDATE_SUBREDDITS);
        sendBroadcast(intent);
    }

    @Override
    public void onFragmentInteraction(ListingData listing) {
        Intent intent = new Intent(this, FullscreenActivity.class);
        intent.putExtra(FullscreenActivity.TITLE,listing.getTitle());
        intent.putExtra(FullscreenActivity.URL,listing.getThumbnail());
        startActivity(intent);
    }
}
