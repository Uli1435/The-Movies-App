package com.example.ulrich.themoviesapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 14.06.18 / 12:09.
 */
public class MoviesJsonUtils {

    public static List<Movies> getMoviesInfoStringFromJson(String json)
        throws JSONException{

        List<Movies> movies = new ArrayList<>();

        final String LIST_RESULTS = "results";
        final String USER_VOTE_AVERAGE = "vote_average";
        final String TITLE = "title";
        final String POSTER_PATH = "poster_path";
        final String POSTER_LANDSCAPE = "backdrop_path";
        final String PLOT_OVERVIEW = "overview";
        final String RELEASE_DATE = "release_date";
        final String MOVIE_ID = "id";


        JSONObject root = new JSONObject(json);
        JSONArray resultsArray = root.optJSONArray(LIST_RESULTS);
        for (int i = 0; i < resultsArray.length(); i++){
            JSONObject resultRoot = resultsArray.optJSONObject(i);

            String posterString = resultRoot.optString(POSTER_PATH);
            String landscapePosterString = resultRoot.optString(POSTER_LANDSCAPE);

            String title = resultRoot.optString(TITLE);
            String description = resultRoot.optString(PLOT_OVERVIEW);
            String releaseDate= resultRoot.optString(RELEASE_DATE);

            double averageVoting = resultRoot.optDouble(USER_VOTE_AVERAGE);
            String averageVotingString = String.valueOf(averageVoting);

            String id = resultRoot.getString(MOVIE_ID);

            Movies moviesObject = new Movies(title, posterString, description,
                    averageVotingString, releaseDate, landscapePosterString, id);
            movies.add(moviesObject);
        }
        return movies;
    }

    public static List<Reviews> getReviewsInfoStringFromJson(String reviewJson)
            throws JSONException{

        List<Reviews> reviews = new ArrayList<>();

        final String LIST_RESULTS = "results";
        final String AUTHOR = "author";
        final String CONTENT = "content";
        final String REVIEW_URL = "url";

        JSONObject root = new JSONObject(reviewJson);
        JSONArray resultsArray = root.optJSONArray(LIST_RESULTS);
        for (int i = 0; i < resultsArray.length(); i++){
            JSONObject resultRoot = resultsArray.optJSONObject(i);

            String authorNameString = resultRoot.optString(AUTHOR);
            String contentString = resultRoot.optString(CONTENT);
            String urlString = resultRoot.optString(REVIEW_URL);

            Reviews reviewObject = new Reviews(authorNameString, contentString, urlString);
            reviews.add(reviewObject);
        }
        return reviews;
    }
}
