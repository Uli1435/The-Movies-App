package com.example.ulrich.themoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 14.06.18 / 11:49.
 */
public class Movies implements Parcelable {

    private String title;
    private String poster;
    private String overview;
    private String ratings;
    private String releasedDate;
    private String landscapePoster;

    public Movies(String title, String poster, String overview, String ratings, String releasedDate,
                  String landscapePoster) {
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.ratings = ratings;
        this.releasedDate = releasedDate;
        this.landscapePoster = landscapePoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getLandscapePoster() {
        return landscapePoster;
    }

    public void setLandscapePoster(String landscapePoster) {
        this.landscapePoster = landscapePoster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.poster);
        dest.writeString(this.overview);
        dest.writeString(this.ratings);
        dest.writeString(this.releasedDate);
        dest.writeString(this.landscapePoster);
    }

    protected Movies(Parcel in) {
        this.title = in.readString();
        this.poster = in.readString();
        this.overview = in.readString();
        this.ratings = in.readString();
        this.releasedDate = in.readString();
        this.landscapePoster = in.readString();
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
