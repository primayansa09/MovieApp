package com.example.moviecatalogue.ui.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviecatalogue.ui.data.remote.RemoteDataSource;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieRepository {

   private volatile static MovieRepository INSTANCE = null;
   private final RemoteDataSource remoteDataSource;
   private MovieResultsItem moviesDetail;
   private TvResultsItem tvShowDetail;

   private static final String TAG = "MovieRepository";

   public FakeMovieRepository(RemoteDataSource remoteDataSource) {
      this.remoteDataSource = remoteDataSource;
   }

   public static MovieRepository getInstance(RemoteDataSource remoteData) {
      if (INSTANCE == null) {
         synchronized (MovieRepository.class) {
            INSTANCE = new MovieRepository(remoteData);
         }
      }
      return INSTANCE;
   }


   public LiveData<List<MovieResultsItem>> getAllMovie() {
      MutableLiveData<List<MovieResultsItem>> movieResult = new MutableLiveData<>();
      remoteDataSource.findMovies(resultsItemList -> {
         ArrayList<MovieResultsItem> movieList = new ArrayList<>();
         for (MovieResultsItem response : resultsItemList) {
            MovieResultsItem item = new MovieResultsItem(
                    response.getOverview(),
                    response.getOriginalLanguage(),
                    response.getOriginalTitle(),
                    response.getTitle(),
                    response.getPosterPath(),
                    response.getBackdropPath(),
                    response.getReleaseDate(),
                    response.getPopularity(),
                    response.getVoteAverage(),
                    response.getId(),
                    response.getVoteCount());

            movieList.add(item);
         }
         movieResult.postValue(movieList);
         Log.d(TAG, "getAllMovie: " + movieList);
      });
      return movieResult;
   }


   public LiveData<MovieResultsItem> getMovieDetail(int movieId) {
      MutableLiveData<MovieResultsItem> detailMovie = new MutableLiveData<>();
      remoteDataSource.findMovies(resultsItemList -> {
         if (resultsItemList != null){
            for (MovieResultsItem response : resultsItemList) {
               if (movieId == response.getId()) {
                  moviesDetail = new MovieResultsItem(
                          response.getOverview(),
                          response.getOriginalLanguage(),
                          response.getOriginalTitle(),
                          response.getTitle(),
                          response.getPosterPath(),
                          response.getBackdropPath(),
                          response.getReleaseDate(),
                          response.getPopularity(),
                          response.getVoteAverage(),
                          response.getId(),
                          response.getVoteCount());
               }

               detailMovie.postValue(moviesDetail);
            }
         }

      });
      return detailMovie;
   }



   public LiveData<List<TvResultsItem>> getAllTvShow() {
      MutableLiveData<List<TvResultsItem>> tvResult = new MutableLiveData<>();
      remoteDataSource.findTvShow(
              resultsItemList -> {
                 ArrayList<TvResultsItem> tvList = new ArrayList<>();
                 for (TvResultsItem response : resultsItemList){
                    TvResultsItem item = new TvResultsItem(
                            response.getFirstAirDate(),
                            response.getOverview(),
                            response.getOriginalLanguage(),
                            response.getPosterPath(),
                            response.getBackdropPath(),
                            response.getOriginalName(),
                            response.getPopularity(),
                            response.getVoteAverage(),
                            response.getName(),
                            response.getId(),
                            response.getVoteCount());

                    tvList.add(item);
                 }
                 tvResult.postValue(tvList);
              });
      return tvResult;
   }


   public LiveData<TvResultsItem> getTvDetail(int TvId) {
      MutableLiveData<TvResultsItem> tvDetail = new MutableLiveData<>();
      remoteDataSource.findTvShow(resultsTvItemList -> {
         if (resultsTvItemList != null){
            for (TvResultsItem response : resultsTvItemList){
               if (TvId == response.getId()){
                  tvShowDetail = new TvResultsItem(
                          response.getFirstAirDate(),
                          response.getOverview(),
                          response.getOriginalLanguage(),
                          response.getPosterPath(),
                          response.getBackdropPath(),
                          response.getOriginalName(),
                          response.getPopularity(),
                          response.getVoteAverage(),
                          response.getName(),
                          response.getId(),
                          response.getVoteCount());

               }
               tvDetail.postValue(tvShowDetail);
            }
         }

      });
      return tvDetail;
   }
}
