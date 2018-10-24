package com.example.bsing.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import java.net.URL;
import java.io.IOException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;




public class MainActivity extends AppCompatActivity {

    TextView text;
    private EditText mSearchBoxEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);


    }
    public void display(String message){
        text.setText(message);

    }

    private URL makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.builder_url(githubQuery);
        String urlString = githubSearchUrl.toString();
        Log.d("mycode", urlString);
        return githubSearchUrl;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            URL url = makeGithubSearchQuery();
            httpRequestUrl task = new httpRequestUrl();
            task.execute(url);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class httpRequestUrl extends AsyncTask<URL, Void, String> {
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

            String displayUrl = doInBackground();
            display(displayUrl);

        }


    }
}

