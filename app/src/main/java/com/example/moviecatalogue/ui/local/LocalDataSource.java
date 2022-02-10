package com.example.moviecatalogue.ui.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.local.room.MovieTvDao;
import java.util.List;

public class LocalDataSource {

   private static LocalDataSource INSTANCE;
   private final MovieTvDao mMovieDao;


   public LocalDataSource(MovieTvDao mMovieDao) {
      this.mMovieDao = mMovieDao;
   }

   public static LocalDataSource getInstance(MovieTvDao movieTvDao){
      if (INSTANCE == null){
         INSTANCE = new LocalDataSource(movieTvDao);
      }
      return INSTANCE;
   }

   public DataSource.Factory<Integer, MovieResultsItem> getAllMovie(){
      return mMovieDao.getMovies();
   }

   public DataSource.Factory<Integer, TvResultsItem> getAllTv(){
      return mMovieDao.getTvShows();
   }

   public DataSource.Factory<Integer, MovieResultsItem> getFavMovies(){
      return mMovieDao.getMovieFavorites();
   }

   public DataSource.Factory<Integer, TvResultsItem> getFavTv(){
      return mMovieDao.getTvFavorites();
   }

   public LiveData<MovieResultsItem> getMovieById(int movieId){
      return mMovieDao.getMovieById(movieId);
   }

   public LiveData<TvResultsItem> getTvById(int tvId){
      return mMovieDao.getTvById(tvId);
   }

   public void setMovieFavorite(MovieResultsItem movie, boolean newState){
      movie.setFavorite(newState);
      mMovieDao.updateMovie(movie);
   }

   public void setTvFavorite(TvResultsItem tvShow, boolean newState){
      tvShow.setFavorite(newState);
      mMovieDao.updateTv(tvShow);
   }

   public void insertMovies(List<MovieResultsItem> movie){
      mMovieDao.insertMovie(movie);
   }

   public void updateMovie(MovieResultsItem movie){
      mMovieDao.updateMovie(movie);
   }

   public void insertTvShow(List<TvResultsItem> tv){
      mMovieDao.insertTv(tv);
   }

  public void updateTvShow(TvResultsItem tvShow){
      mMovieDao.updateTv(tvShow);
   }
}
