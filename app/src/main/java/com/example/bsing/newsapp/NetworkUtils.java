package com.example.bsing.newsapp;

import android.net.Uri;
import android.net.UrlQuerySanitizer;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Object;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class NetworkUtils {
    final static String Base_Url = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=";
    final static String Param_Query ="85826d19e37d4f80bc965f6c9710b8f0";


    public static URL builder_url () {
         Uri builtUri = Uri.parse(Base_Url).buildUpon().appendQueryParameter(Param_Query,"85826d19e37d4f80bc965f6c9710b8f0").build();
         URL url = null;
         try {
             url = new URL(builtUri.toString());

         }catch (MalformedURLException e){
             e.printStackTrace();
         }

       return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();

            }else {
                return null;
            }

        }finally {
            urlConnection.disconnect();
        }
    }
}
