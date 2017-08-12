package com.example.android.newsapp;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsInfo>>, SwipeRefreshLayout.OnRefreshListener {

    private static final int LOADER_ID = 0;

    InfoAdapter infoAdapter;

    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        ListView listview = (ListView) findViewById(R.id.listview);

        infoAdapter = new InfoAdapter(MainActivity.this);

        listview.setAdapter(infoAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent contentBrowser = new Intent("android.intent.action.VIEW", Uri.parse(infoAdapter.getItem(position).getWebUrl()));
                startActivity(contentBrowser);
            }
        });

        if (Utility.isNetworkAvailable(MainActivity.this)) {
            getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRefresh() {
        if (Utility.isNetworkAvailable(MainActivity.this)) {
            getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
            swipe.setRefreshing(false);
        }
    }

    @Override
    public Loader<List<NewsInfo>> onCreateLoader(int id, Bundle args) {
        return new NewsInfoService(this);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsInfo>> loader, List<NewsInfo> data) {
        swipe.setRefreshing(false);
        if (null != data) {
            infoAdapter.clear();
            infoAdapter.addFeeds(data);
            infoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsInfo>> loader) {

    }
}

