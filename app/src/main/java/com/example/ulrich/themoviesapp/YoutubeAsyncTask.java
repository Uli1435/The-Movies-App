package com.example.ulrich.themoviesapp;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

/**
 * Created on 24.06.18 / 11:32.
 */
public class YoutubeAsyncTask extends AsyncTask<String, Void, List<Trailers>>{
    private List<Trailers> parseMovieTrailers;
    private WeakReference<YoutubeActivity> youtubeActivityWeakReference;

    public YoutubeAsyncTask(YoutubeActivity activity) {
        youtubeActivityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        YoutubeActivity activity = youtubeActivityWeakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }
    }

    @Override
    protected List<Trailers> doInBackground(String... strings) {
        URL reviewsUrl = NetworkUtils.buildTrailersUrl(strings[0]);
        try {
            String jsonMoviesTrailersResponse = NetworkUtils.getResponseFromHttpUrlForTrailers(reviewsUrl);
            parseMovieTrailers = MoviesJsonUtils.getTrailersInfoStringFromJson(jsonMoviesTrailersResponse);
            return parseMovieTrailers;
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Trailers> trailers) {
        super.onPostExecute(trailers);

        YoutubeActivity activity = youtubeActivityWeakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }

        activity.trailersAdapter = new YoutubeAdapter(trailers, activity);

        if (trailers != null && !trailers.isEmpty()){
            activity.trailersRecyclerView.setAdapter(activity.trailersAdapter);
            activity.trailersAdapter.notifyDataSetChanged();
        } else {
            activity.noTrailersTextView.setVisibility(View.VISIBLE);
            activity.trailersRecyclerView.setVisibility(View.INVISIBLE);
        }


    }
}
