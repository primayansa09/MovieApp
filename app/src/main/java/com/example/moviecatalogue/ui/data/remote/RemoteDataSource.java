package com.example.moviecatalogue.ui.data.remote;

import static com.example.moviecatalogue.ui.until.Const.API_KEY;
import static com.example.moviecatalogue.ui.until.Const.MOVIE_ID;
import static com.example.moviecatalogue.ui.until.Const.TVSHOW_ID;

import android.util.Log;

import com.example.moviecatalogue.ui.api.ApiConfig;
import com.example.moviecatalogue.ui.data.response.MovieResponse;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.response.TvShowResponse;
import com.example.moviecatalogue.ui.until.Const;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;

    private static final String TAG = "RemoteDataSource";

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    public void findMovies(LoadMovieCallback callback) {
        ApiConfig.getApiservice().getMovieresponse(API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.getMovies(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
            }

        });

    }

    public void findTvShow(LoadTvCallback callback){
       ApiConfig.getApiservice().getTvResponse(API_KEY).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        callback.getTvShow(response.body().getResults());

                        Log.d(TAG, "onResponse: " + response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public interface LoadTvCallback{
        void getTvShow(List<TvResultsItem> resultsTvItemList);
    }

    public interface LoadMovieCallback{
        void getMovies(List<MovieResultsItem> resultsItemList);
    }
}
