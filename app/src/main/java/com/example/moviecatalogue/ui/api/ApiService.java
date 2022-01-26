package com.example.moviecatalogue.ui.api;

import static com.example.moviecatalogue.ui.until.Const.URL_MOVIE;
import static com.example.moviecatalogue.ui.until.Const.URL_TVSHOW;

import com.example.moviecatalogue.BuildConfig;
import com.example.moviecatalogue.ui.data.response.MovieResponse;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET(URL_MOVIE)
    Call<MovieResponse> getMovieresponse(
            @Query("api_key") String api_key);


    @GET(URL_TVSHOW)
    Call<TvShowResponse> getTvResponse(
            @Query("api_key") String api_key);
}
