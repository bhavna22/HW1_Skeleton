package com.example.bsing.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.net.URL;
import java.net.HttpURLConnection;


public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);


    }
    public void display(String message){
        text.setText(message);

    }

    public class GitHubQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
          URL stringUrl=  NetworkUtils.builder_url();
          String s=null;
          try {

              s= NetworkUtils.getResponseFromHttpUrl(stringUrl);
          }
          catch (Exception e)
          {
              System.out.println("Error");
          }

          display(s);
           return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }


    }
}

