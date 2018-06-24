package com.example.ulrich.themoviesapp;

/**
 * Created on 24.06.18 / 11:07.
 */
public class Trailers {

    private String mKeyForYoutubeThumbnail;
    private String mYoutubeTitle;

    public Trailers(String mKeyForYoutubeThumbnail, String mYoutubeTitle) {
        this.mKeyForYoutubeThumbnail = mKeyForYoutubeThumbnail;
        this.mYoutubeTitle = mYoutubeTitle;
    }

    public String getKeyForYoutubeThumbnail() {
        return mKeyForYoutubeThumbnail;
    }

    public void setKeyForYoutubeThumbnail(String mKeyForYoutubeThumbnail) {
        this.mKeyForYoutubeThumbnail = mKeyForYoutubeThumbnail;
    }

    public String getYoutubeTitle() {
        return mYoutubeTitle;
    }

    public void setYoutubeTitle(String mYoutubeTitle) {
        this.mYoutubeTitle = mYoutubeTitle;
    }
}
