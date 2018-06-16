package com.example.ulrich.themoviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * Created on 14.06.18 / 13:49.
 */
public class InfoActivity extends AppCompatActivity {

    private ImageView poster;
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

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        poster = findViewById(R.id.poster_image_view);
        landscapePosterImageView = findViewById(R.id.landscape_poster_image_view);
        movieTitle = findViewById(R.id.movie_title_textView);
        rating = findViewById(R.id.rating_textView);
        releaseDate = findViewById(R.id.release_date);
        description = findViewById(R.id.description_textView);



        movieTitle.setText(getIntent().getStringExtra("title"));
        rating.setText(getIntent().getStringExtra("rating"));
        releaseDate.setText(getIntent().getStringExtra("releasedDate"));
        description.setText(getIntent().getStringExtra("overview"));

        String posterString = getIntent().getStringExtra("poster");
        String backgroundPosterString = getIntent().getStringExtra("backgroundPoster");

        Picasso.get()
                .load(BASE_IMAGE_URL + posterString)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_available_placeholder)
                .into(poster);

        Picasso.get()
                .load(BASE_IMAGE_URL + backgroundPosterString)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_available_placeholder)
                .into(landscapePosterImageView);
    }
}