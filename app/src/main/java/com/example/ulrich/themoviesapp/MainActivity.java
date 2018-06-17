package com.example.ulrich.themoviesapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MoviesAdapter mAdapter;
    private RecyclerView mMoviesListRecyclerView;
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
        //so that the statusbar don't flash
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

    public static class MoviesAsyncTask extends AsyncTask<String, Void, List<Movies>> {

        private WeakReference<MainActivity> mainActivityWeakReference;

        private List<Movies> parseMovieList;
        private Context mContext;

        public MoviesAsyncTask(MainActivity activity) {
            mainActivityWeakReference = new WeakReference<>(activity);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity activity = mainActivityWeakReference.get();
            if (activity ==null || activity.isFinishing()){
                return;
            }
        }

        @Override
        protected List<Movies> doInBackground(String... strings) {

            URL moviesUrl = NetworkUtils.buildBaseUrl(strings[0]);

            try {
                String jsonMoviesRespond = NetworkUtils.getResponseFromHttpUrl(moviesUrl);
                parseMovieList = MoviesJsonUtils.getMoviesPosterStringFromJson(jsonMoviesRespond);
                return parseMovieList;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<Movies> moviesList) {
            super.onPostExecute(moviesList);

            MainActivity activity = mainActivityWeakReference.get();
            if (activity ==null || activity.isFinishing()){
                return;
            }

            activity.mAdapter = new MoviesAdapter(moviesList, mContext);

            if (moviesList != null && !moviesList.isEmpty()) {
                activity.mMoviesListRecyclerView.setAdapter(activity.mAdapter);
                activity.mAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(mContext, "Oops... Something went wrong...",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.sort_by_menu_item){
            AlertDialogWithRadioButtonGroup();
        }

        return super.onOptionsItemSelected(item);
    }

    private void AlertDialogWithRadioButtonGroup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.show)
                .setSingleChoiceItems(R.array.choices, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //When switching options
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //When pressing the ok button

                        int radioButton = ((AlertDialog) dialogInterface).getListView().getCheckedItemPosition();
                        switch (radioButton){
                            case 0:
                                loadMovieData(POPULAR_ORDER);
                                break;
                            case 1:
                                loadMovieData(AVERAGE_ORDER);
                                break;
                            case 2:
                                loadMovieData(UPCOMING_ORDER);
                                break;
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //When pressing the cancel button
                        dialogInterface.cancel();
                    }
                })
                .show();
    }
}
