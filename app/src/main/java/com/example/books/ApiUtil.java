package com.example.books;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

//this class contain static methods
public class ApiUtil {
    //removing the constractor
    private ApiUtil(){}
    //creating a constant for the base url
    public static final String BASE_API_URL =
            "https://www.googleapis.com/books/v1/volumes";
    public static final String QUERY_PARAMETER_KEY = "q";
    public static final String KEY = "key";
    public static final String API_KEY ="";

     //query for tittle
    //func 4 title
    public static URL buildUrl(String title) {
//        String fullUrl = BASE_API_URL + "?q=" + title;
        //conveting string to url
        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY,title)
                .appendQueryParameter(KEY,API_KEY)
                .build();

        try {
            url = new URL(uri.toString());

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }
    //conectig to api
    public static String getJson(URL url) throws IOException {
        //establishing a connection to the url that was passed

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //reading the data


        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            //coverting a stream to string
            scanner.useDelimiter("\\A");

            //returning the data
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        }
        catch (Exception e){
            Log.d("Error" ,e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }
    }
}
