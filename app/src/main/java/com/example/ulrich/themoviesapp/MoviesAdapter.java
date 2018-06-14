package com.example.ulrich.themoviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 14.06.18 / 12:53.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{


    private List<Movies> mMoviesList;
    private Context mContext;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";
    private CustomItemClickListener mListener;

    public MoviesAdapter(List<Movies> moviesList, CustomItemClickListener listener){
        mMoviesList = moviesList;
        mListener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movies_list,
                viewGroup, false);
        return new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView posterImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            posterImageView = itemView.findViewById(R.id.poster_image_view);
        }
    }
}
