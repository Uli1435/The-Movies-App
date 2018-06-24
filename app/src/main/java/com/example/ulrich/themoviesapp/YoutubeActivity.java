package com.example.ulrich.themoviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class YoutubeActivity extends AppCompatActivity {

    private List<Trailers> mTrailersList;
    RecyclerView trailersRecyclerView;
    YoutubeAdapter trailersAdapter;
    ImageView youtubeThumbnail;
    TextView youtubeMovieTitle;
    TextView noTrailersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        youtubeThumbnail = findViewById(R.id.youtube_thumbnail_image_view);
        youtubeMovieTitle = findViewById(R.id.youtube_movie_name_text_view);
        noTrailersTextView = findViewById(R.id.youtube_negative_text_view);

        mTrailersList = new ArrayList<>();
        trailersRecyclerView = findViewById(R.id.youtube_recycler_view);
        trailersRecyclerView.setHasFixedSize(true);
        trailersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trailersAdapter = new YoutubeAdapter(mTrailersList, this);
        trailersRecyclerView.setAdapter(trailersAdapter);

        Intent intent = getIntent();
        String moviesIdString = Objects.requireNonNull(intent.getExtras()).getString("Movies Id String");
        new YoutubeAsyncTask(this).execute(moviesIdString);
    }
}
