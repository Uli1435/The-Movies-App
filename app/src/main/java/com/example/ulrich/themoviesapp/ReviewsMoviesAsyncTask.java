package com.example.ulrich.themoviesapp;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

/**
 * Created on 23.06.18 / 13:23.
 */
public class ReviewsMoviesAsyncTask extends AsyncTask<String, Void, List<Reviews>> {
    private List<Reviews> parseMovieReviews;
    private WeakReference<InfoActivity> infoActivityWeakReference;

    public ReviewsMoviesAsyncTask(InfoActivity activity){
        infoActivityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        InfoActivity activity = infoActivityWeakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }
    }

    @Override
    protected List<Reviews> doInBackground(String... strings) {
        URL reviewsUrl = NetworkUtils.buildReviewsUrl(strings[0]);
        try {
            String jsonMoviesReviewResponse = NetworkUtils.getResponseFromHttpUrlForReviews(reviewsUrl);
            parseMovieReviews = MoviesJsonUtils.getReviewsInfoStringFromJson(jsonMoviesReviewResponse);
            return parseMovieReviews;
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Reviews> reviewList) {
        super.onPostExecute(reviewList);

        InfoActivity activity = infoActivityWeakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }

        activity.mReviewsAdapter = new ReviewsAdapter(reviewList, activity);
        if (reviewList != null && !reviewList.isEmpty()){
            activity.mReviewsRecyclerView.setAdapter(activity.mReviewsAdapter);
            activity.mReviewsAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(activity, "No reviews to show!!!", Toast.LENGTH_LONG).show();
        }

    }
}
