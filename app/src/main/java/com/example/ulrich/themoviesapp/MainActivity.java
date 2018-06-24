package com.example.ulrich.themoviesapp;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MoviesAdapter mAdapter;
    RecyclerView mMoviesListRecyclerView;
    private List<Movies> mMovieItems;
    private GridLayoutManager layoutManager;
    private final String POPULAR_ORDER = "popular";
    private final String AVERAGE_ORDER = "top_rated";
    private final String UPCOMING_ORDER = "upcoming";
    private final String DEFAULT_VALUE = POPULAR_ORDER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //When transitioning from one activity to another
        //so that the status bar don't flash
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        mMovieItems = new ArrayList<>();

        mMoviesListRecyclerView = findViewById(R.id.recycler_view);
        mMoviesListRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        mMoviesListRecyclerView.setLayoutManager(layoutManager);


        mAdapter = new MoviesAdapter(mMovieItems, this);

        mMoviesListRecyclerView.setAdapter(mAdapter);

        loadMovieData(DEFAULT_VALUE);
    }

    private void loadMovieData(String value) {
        new MoviesAsyncTask(this).execute(value);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int popularMovies = R.id.popular_movies;
        final int topRated = R.id.top_rated;
        final int upcomingMovies = R.id.upcoming;

        int menuItemThatWasSelected = item.getItemId();
            switch (menuItemThatWasSelected){
                case popularMovies:
                    loadMovieData(POPULAR_ORDER);
                    Toast.makeText(this, getResources().getString(R.string.most_popular), Toast.LENGTH_LONG).show();
//                    Snackbar.make( findViewById(android.R.id.content),"Most Popular", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                    break;
                case topRated:
                    loadMovieData(AVERAGE_ORDER);
                    Toast.makeText(this, getResources().getString(R.string.top_rated)
                            , Toast.LENGTH_LONG).show();
                    break;
                case upcomingMovies:
                    loadMovieData(UPCOMING_ORDER);
                    Toast.makeText(this, getResources().getString(R.string.upcoming_movies), Toast.LENGTH_LONG).show();
                    break;
            }

        return super.onOptionsItemSelected(item);
    }
}
