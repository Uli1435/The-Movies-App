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
        new MoviesAsyncTask().execute();
    }

    public static class MoviesAsyncTask extends AsyncTask<String, Void, List<Movies>>{

        private WeakReference<MainActivity> mainActivity;
        private WeakReference<MoviesAdapter> adapterWeakReference;
        private WeakReference<RecyclerView> recyclerViewWeakReference;
        private List<Movies> parseMovieList;
        private WeakReference<CustomItemClickListener> mListener;
        private Context mContext;

        public MoviesAsyncTask(Context context, WeakReference<CustomItemClickListener> listener){
            mContext = context;
            mListener = listener;
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

        }
    }
}
