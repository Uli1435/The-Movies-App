package com.example.ulrich.themoviesapp;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

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
    private WeakReference<ReviewsActivity> reviewsActivityWeakReference;

    public ReviewsMoviesAsyncTask(ReviewsActivity activity){
        reviewsActivityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ReviewsActivity activity = reviewsActivityWeakReference.get();
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

        ReviewsActivity activity = reviewsActivityWeakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }

        activity.reviewsAdapter = new ReviewsAdapter(reviewList, activity);
        if (reviewList != null && !reviewList.isEmpty()){
            activity.reviewsRecyclerView.setAdapter(activity.reviewsAdapter);
            activity.reviewsAdapter.notifyDataSetChanged();
        } else {
            activity.noReviewsTextView.setVisibility(View.VISIBLE);
            activity.reviewsRecyclerView.setVisibility(View.INVISIBLE);
        }

    }
}
