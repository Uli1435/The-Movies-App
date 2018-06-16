package com.example.ulrich.themoviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private final String DEFAULT_VALUE = POPULAR_ORDER;

    private CustomItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieItems = new ArrayList<>();

        mMoviesListRecyclerView = findViewById(R.id.recycler_view);
        mMoviesListRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        mMoviesListRecyclerView.setLayoutManager(layoutManager);


        mAdapter = new MoviesAdapter(mMovieItems, new CustomItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("###################", "clicked position:" + position);


                Movies myMovies = mMovieItems.get(position);

//                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
//                intent.putExtra("movies", (Parcelable) myMovies);
            }
        });

        mMoviesListRecyclerView.setAdapter(mAdapter);

        loadMovieData(DEFAULT_VALUE);
    }

    private void loadMovieData(String value){
        new MoviesAsyncTask(this).execute(value);
    }

    public static class MoviesAsyncTask extends AsyncTask<String, Void, List<Movies>>{

        private MoviesAdapter mMoviesAdapter;
        private RecyclerView mRecyclerView;
        private List<Movies> parseMovieList;
        private CustomItemClickListener mListener;
        private Context mContext;

        public MoviesAsyncTask(Context context){
            mContext = context;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Movies> doInBackground(String... strings) {

            URL moviesUrl = NetworkUtils.buildBaseUrl(strings[0]);

            try {
                String jsonMoviesRespond = NetworkUtils.getResponseFromHttpUrl(moviesUrl);
                parseMovieList = MoviesJsonUtils.getMoviesPosterStringFromJson(jsonMoviesRespond);
                return parseMovieList;
            } catch (IOException | JSONException e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<Movies> moviesList) {
            super.onPostExecute(moviesList);

            mMoviesAdapter = new MoviesAdapter(moviesList, mListener);
            if (moviesList != null && !moviesList.isEmpty()){
                mRecyclerView.setAdapter(mMoviesAdapter);
                mMoviesAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(mContext, "Oops... Something went wrong...",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
