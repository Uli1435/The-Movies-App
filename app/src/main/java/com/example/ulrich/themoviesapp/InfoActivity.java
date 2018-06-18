package com.example.ulrich.themoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private RatingBar ratingBar;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    private static final String BACKGROUND_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Snackbar.make(view, "Added to your Favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.like_complete_white_24dp));
            }
        });

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setFocusable(false);
        //Use the listener only for the rating bar to not be touchable
        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        poster = findViewById(R.id.poster_image_view);
        landscapePosterImageView = findViewById(R.id.landscape_poster_image_view);
        movieTitle = findViewById(R.id.movie_title_textView);
        rating = findViewById(R.id.rating_textView);
        releaseDate = findViewById(R.id.release_date);
        description = findViewById(R.id.description_textView);


        setUpDetails();

    }



    public void setUpDetails(){
        //When transitioning from one activity to another
        //so that the status bar don't flash
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);


        Intent intent = getIntent();
        Movies movies = intent.getParcelableExtra("Movie Infos");
        movieTitle.setText(movies.getTitle());
        String posterString = movies.getPoster();
        description.setText(movies.getOverview());
        String ratingsString = movies.getRatings();
        float ratingsFloat = Float.parseFloat(ratingsString);
        ratingBar.setRating(ratingsFloat / 2);
        rating.setText(movies.getRatings());
        String dateString = movies.getReleasedDate();
        String backgroundPosterString = movies.getLandscapePoster();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(dateString);
            SimpleDateFormat simpleDateFormatOutput = new SimpleDateFormat("dd-MM-yyyy");
            dateString = simpleDateFormatOutput.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        releaseDate.setText(dateString);



        Picasso.get()
                .load(BASE_IMAGE_URL + posterString)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_available_placeholder)
                .into(poster);

        Picasso.get()
                .load(BACKGROUND_IMAGE_URL + backgroundPosterString)
                .placeholder(R.drawable.background_placeholder)
                .error(R.drawable.no_image_available_placeholder)
                .into(landscapePosterImageView);
    }


}