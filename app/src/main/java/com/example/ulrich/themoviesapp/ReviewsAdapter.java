package com.example.ulrich.themoviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created on 23.06.18 / 09:42.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private List<Reviews> mReviewList;
    private Context mContext;

    public ReviewsAdapter(List<Reviews> mReviewList, Context mContext) {
        this.mReviewList = mReviewList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_review_layout
                , viewGroup, false);



        return new ViewHolder(view, mContext, mReviewList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reviews reviews = mReviewList.get(position);

        holder.authorName.setText(reviews.getAuthor());
        holder.review.setText(reviews.getContent());

    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView authorName;
        TextView review;
        Context context;
        List<Reviews> myReviews;

        public ViewHolder(@NonNull View itemView, Context context, List<Reviews> reviews) {
            super(itemView);

            myReviews = reviews;
            this.context = context;

            authorName = itemView.findViewById(R.id.author_name_text_view);
            review = itemView.findViewById(R.id.the_review_text_view);
        }
    }
}
