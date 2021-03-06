package com.example.ulrich.themoviesapp;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created on 14.06.18 / 12:42.
 */
public class NetworkUtils {

    private final static String THE_MOVIE_DB_BASE_URL = "https://api.themoviedb.org/3/movie/";

    private final static String API_KEY = "api_key";

    private final static String API_KEY_VALUE = "XXXXXXXXXXXXXXXX";

    private final static String API_LANGUAGE = "language=en-US";

    private final static String REVIEWS = "reviews";

    private final static String VIDEOS = "videos";

    public static URL buildBaseUrl(String sortBy){
        Uri buildUri = Uri.parse(THE_MOVIE_DB_BASE_URL).buildUpon()
                .appendPath(sortBy)
                .appendQueryParameter(API_KEY, API_KEY_VALUE)
                .appendQueryParameter("Language", API_LANGUAGE)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }



    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput){
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildReviewsUrl(String moviesId){
        Uri buildUri = Uri.parse(THE_MOVIE_DB_BASE_URL).buildUpon()
                .appendPath(moviesId)
                .appendPath(REVIEWS)
                .appendQueryParameter(API_KEY, API_KEY_VALUE)
                .appendQueryParameter("", API_LANGUAGE)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static  String getResponseFromHttpUrlForReviews(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput){
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildTrailersUrl(String moviesId){
        Uri buildUri = Uri.parse(THE_MOVIE_DB_BASE_URL).buildUpon()
                .appendPath(moviesId)
                .appendPath(VIDEOS)
                .appendQueryParameter(API_KEY, API_KEY_VALUE)
                .appendQueryParameter("", API_LANGUAGE)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static  String getResponseFromHttpUrlForTrailers(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput){
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
