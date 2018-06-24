package com.example.ulrich.themoviesapp;

/**
 * Created on 24.06.18 / 11:07.
 */
public class Trailers {

    private String mYoutubeThumbnail;
    private String mYoutubeTitle;

    public Trailers(String mYoutubeThumbnail, String mYoutubeTitle) {
        this.mYoutubeThumbnail = mYoutubeThumbnail;
        this.mYoutubeTitle = mYoutubeTitle;
    }

    public String getmYoutubeThumbnail() {
        return mYoutubeThumbnail;
    }

    public void setmYoutubeThumbnail(String mYoutubeThumbnail) {
        this.mYoutubeThumbnail = mYoutubeThumbnail;
    }

    public String getmYoutubeTitle() {
        return mYoutubeTitle;
    }

    public void setmYoutubeTitle(String mYoutubeTitle) {
        this.mYoutubeTitle = mYoutubeTitle;
    }
}
