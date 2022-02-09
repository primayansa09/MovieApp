package com.example.moviecatalogue.ui.data.remote;
import static com.example.moviecatalogue.ui.until.Const.API_KEY;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviecatalogue.ui.api.ApiConfig;
import com.example.moviecatalogue.ui.data.response.MovieResponse;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.response.TvShowResponse;
import com.example.moviecatalogue.ui.until.EspressoIdlingResource;

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

    public LiveData<ApiResponse<List<MovieResultsItem>>> findMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResultsItem>>> resultMovie = new MutableLiveData<>();
        ApiConfig.getApiservice().getMovieresponse(API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    List result = response.body().getResults();
                    if (response.body() != null) {
                        resultMovie.postValue(ApiResponse.success(result));
                        EspressoIdlingResource.decrement();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    EspressoIdlingResource.decrement();
            }

        });
        return resultMovie;

    }

    public LiveData<ApiResponse<List<TvResultsItem>>> findTvShow(){
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvResultsItem>>> resultTv = new MutableLiveData<>();
       ApiConfig.getApiservice().getTvResponse(API_KEY).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.isSuccessful()){
                    List result = response.body().getResults();
                    if (response.body() != null){
                        resultTv.postValue(ApiResponse.success(result));
                        EspressoIdlingResource.decrement();
                        Log.d(TAG, "onResponse: " + response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                EspressoIdlingResource.decrement();
            }
        });
       return resultTv;
    }
}
