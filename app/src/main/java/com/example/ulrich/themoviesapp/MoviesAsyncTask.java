package com.example.ulrich.themoviesapp;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

/**
 * Created on 17.06.18 / 18:03.
 */
public class MoviesAsyncTask extends AsyncTask<String, Void, List<Movies>>{



    private WeakReference<MainActivity> mainActivityWeakReference;

    private List<Movies> parseMoviesList;

    public MoviesAsyncTask(MainActivity activity){
        mainActivityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        MainActivity activity = mainActivityWeakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }
    }

    @Override
    protected List<Movies> doInBackground(String... strings) {

        URL moviesUrl = NetworkUtils.buildBaseUrl(strings[0]);

        try {
            String jsonMoviesRespond = NetworkUtils.getResponseFromHttpUrl(moviesUrl);
            parseMoviesList = MoviesJsonUtils.getMoviesPosterStringFromJson(jsonMoviesRespond);
            return parseMoviesList;
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(List<Movies> moviesList) {
        super.onPostExecute(moviesList);

        MainActivity activity = mainActivityWeakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }

        activity.mAdapter = new MoviesAdapter(moviesList, activity);

        if (moviesList != null && !moviesList.isEmpty()){
            activity.mMoviesListRecyclerView.setAdapter(activity.mAdapter);
            activity.mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(activity, "Oops... Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}
