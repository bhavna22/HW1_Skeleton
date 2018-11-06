package com.example.bsing.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.EditText;
import java.net.URL;
import java.io.IOException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    protected MenuItem refreshItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.get_news, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                this.refreshItem = item;
                item.setVisible(false); // hide refresh button

                Toast.makeText(getApplicationContext(), "REFRESH CLICKED", Toast.LENGTH_SHORT).show();
                new NewsQueryTask().execute();
                return true;
        }
        return false;
    }




    public class NewsQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            String httpRequest = "";
            try {
               httpRequest  = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return httpRequest;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            refreshItem.setVisible(true);


        }


    }
}

