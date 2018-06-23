package com.example.ulrich.themoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 23.06.18 / 08:44.
 */
public class Reviews implements Parcelable {

    private String author;
    private String content;
    private String reviewUrl;

    public Reviews(String author, String content, String reviewUrl) {
        this.author = author;
        this.content = content;
        this.reviewUrl = reviewUrl;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.content);
        dest.writeString(this.reviewUrl);
    }

    protected Reviews(Parcel in) {
        this.author = in.readString();
        this.content = in.readString();
        this.reviewUrl = in.readString();
    }

    public static final Parcelable.Creator<Reviews> CREATOR = new Parcelable.Creator<Reviews>() {
        @Override
        public Reviews createFromParcel(Parcel source) {
            return new Reviews(source);
        }

        @Override
        public Reviews[] newArray(int size) {
            return new Reviews[size];
        }
    };
}
