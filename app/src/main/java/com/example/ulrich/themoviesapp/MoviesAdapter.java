package com.example.ulrich.themoviesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created on 14.06.18 / 12:53.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{


    private List<Movies> mMoviesList ;
    private Context mContext;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    public MoviesAdapter(List<Movies> moviesList, Context context){
        mMoviesList = moviesList;
        mContext = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movies_list,
                viewGroup, false);

        return new ViewHolder(view, mContext, mMoviesList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies movies = mMoviesList.get(position);



        String posterPath = movies.getPoster();
        Picasso.get()
                .load(BASE_IMAGE_URL + posterPath)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_available_placeholder)
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView posterImageView;
        List<Movies> myMovies ;
        Context context;

        public ViewHolder(@NonNull View itemView, Context ctx, List<Movies> movies) {
            super(itemView);

            myMovies = movies;
            context = ctx;

            itemView.setOnClickListener(this);

            posterImageView = itemView.findViewById(R.id.poster_image_view);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Movies movies = myMovies.get(position);

            Intent intent = new Intent(context, InfoActivity.class);
            intent.putExtra("Movie Infos", movies);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //For the activity transition
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                        ((Activity) context, posterImageView, Objects.requireNonNull(ViewCompat
                                .getTransitionName(posterImageView)));

                context.startActivity(intent, options.toBundle());
            }
            context.startActivity(intent);
        }
    }
}
