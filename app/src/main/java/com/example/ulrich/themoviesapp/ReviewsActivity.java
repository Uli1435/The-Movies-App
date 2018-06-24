package com.example.ulrich.themoviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReviewsActivity extends AppCompatActivity {

    private List<Reviews> mReviewsMoviesList;
    RecyclerView reviewsRecyclerView;
    ReviewsAdapter reviewsAdapter;
    TextView noReviewsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        noReviewsTextView = findViewById(R.id.negative_text_view);
        mReviewsMoviesList = new ArrayList<>();
        reviewsRecyclerView = findViewById(R.id.activity_reviews_recycler_view);
        reviewsRecyclerView.setHasFixedSize(true);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsAdapter = new ReviewsAdapter(mReviewsMoviesList, this);
        reviewsRecyclerView.setAdapter(reviewsAdapter);

        Intent intent = getIntent();
        String moviesIdString = Objects.requireNonNull(intent.getExtras()).getString("Movies Id String");
        new ReviewsMoviesAsyncTask(this).execute(moviesIdString);

    }
}
