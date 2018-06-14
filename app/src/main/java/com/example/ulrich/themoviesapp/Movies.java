package com.example.ulrich.themoviesapp;

/**
 * Created on 14.06.18 / 11:49.
 */
public class Movies {

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
}
