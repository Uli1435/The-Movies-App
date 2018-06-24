package com.example.ulrich.themoviesapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 24.06.18 / 11:10.
 */
public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {

    private List<Trailers> mTrailersList;
    private Context mContext;
    private static final String YOUTUBE_IMAGE_URL = "https://img.youtube.com/vi/";
    private static final String YOUTUBE_IMAGE_SUFFIX = "/0.jpg";

    public YoutubeAdapter(List<Trailers> mTrailersList, Context mContext) {
        this.mTrailersList = mTrailersList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_youtube_layout
                , viewGroup, false);
        return new ViewHolder(view, mContext, mTrailersList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Trailers trailers = mTrailersList.get(position);

        String thumbnailPath = trailers.getmYoutubeThumbnail();
        Picasso.get()
                .load(YOUTUBE_IMAGE_URL + thumbnailPath + YOUTUBE_IMAGE_SUFFIX)
                .placeholder(R.drawable.background_placeholder)
                .error(R.drawable.no_image_available_placeholder)
                .into(viewHolder.youtubeThumbnailImageView);

        viewHolder.youtubeTitleTextView.setText(trailers.getmYoutubeTitle());

    }

    @Override
    public int getItemCount() {
        return mTrailersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView youtubeThumbnailImageView;
        TextView youtubeTitleTextView;
        List<Trailers> theTrailers;
        Context context;

        public ViewHolder(@NonNull View itemView, Context context, List<Trailers> trailers) {
            super(itemView);

            theTrailers = trailers;
            this.context = context;

            itemView.setOnClickListener(this);

            youtubeThumbnailImageView = itemView.findViewById(R.id.youtube_thumbnail_image_view);
            youtubeTitleTextView = itemView.findViewById(R.id.youtube_movie_name_text_view);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Trailers trailers = theTrailers.get(position);

            //TODO When the trailer is clicked, send it to youtube
            String youtubePath = "https://www.youtube.com/watch?v=";
            String trailersKey = trailers.getmYoutubeThumbnail();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(youtubePath + trailersKey));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }

        }
    }
}

