package com.example.ulrich.themoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created on 14.06.18 / 13:49.
 */
class InfoActivity extends AppCompatActivity {

    private ImageView landscapePosterImageView;
    private TextView movieTitle;
    private TextView rating;
    private TextView releaseDate;
    private TextView description;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        landscapePosterImageView = findViewById(R.id.landscape_poster_image_view);
        movieTitle = findViewById(R.id.movie_title_textView);
        rating = findViewById(R.id.rating_textView);
        releaseDate = findViewById(R.id.release_date);
        description = findViewById(R.id.description_textView);


        Intent intent = getIntent();
        Movies movies = intent.getParcelableExtra("movies");
        String titleString = movies.getTitle();
        String descriptionString = movies.getOverview();
        String ratingsString = movies.getRatings();
        String releasedDateString = movies.getReleasedDate();
        String landscapePosterString = movies.getLandscapePoster();


        movieTitle.setText(titleString);
        description.setText(descriptionString);
        rating.setText(ratingsString);
        releaseDate.setText(releasedDateString);

        Picasso.get()
                .load(BASE_IMAGE_URL + landscapePosterString)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_available_placeholder)
                .into(landscapePosterImageView);



    }
}