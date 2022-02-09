package com.example.moviecatalogue.ui.Favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;

public class FavoriteViewModel extends ViewModel {

   private MovieRepository movieRepository;

   public FavoriteViewModel(MovieRepository movieRepository) {
      this.movieRepository = movieRepository;
   }

   public LiveData<PagedList<MovieResultsItem>> getFavoriteMovies(){
      return movieRepository.getMovieFavorite();
   }

   public LiveData<PagedList<TvResultsItem>> getFavortieTvShows(){
      return movieRepository.getTvFavorite();
   }

   public void setFavoriteData(MovieResultsItem movieResultsItem){
        final boolean newState = !movieResultsItem.isFavorite();
         movieRepository.setMovieFavorite(movieResultsItem, newState);
   }

   public void setFavoriteDataTv(TvResultsItem tvResultsItem){
      final boolean newState = !tvResultsItem.isFavorite();
      movieRepository.setTvFavorite(tvResultsItem, newState);
   }
}
