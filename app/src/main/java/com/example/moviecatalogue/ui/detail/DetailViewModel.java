package com.example.moviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.vo.Resource;

public class DetailViewModel extends ViewModel {

    private MutableLiveData<Integer> movieID = new MutableLiveData<>();
    private MutableLiveData<Integer> tvShowID = new MutableLiveData<>();
    private MovieRepository movieRepository ;

    public void setSelectedMovie(int movieId){
        this.movieID.setValue(movieId);
    }

    public void setSelectedTv(int tvId){
        this.tvShowID.setValue(tvId);
    }

    public DetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<Resource<MovieResultsItem>> movieDetail = Transformations.switchMap(movieID,
            mMovieId -> movieRepository.getMovieById(mMovieId));

    public LiveData<Resource<TvResultsItem>> tvShowDetail = Transformations.switchMap(tvShowID,
            mTvShowId -> movieRepository.getTvById(mTvShowId));

    void setFavoriteMovie(){
            Resource<MovieResultsItem> movieResource = movieDetail.getValue();

                if (movieResource != null){
                    MovieResultsItem movieResultsItem = movieResource.data;
                    final boolean newState = !movieResultsItem.isFavorite();
                    movieRepository.setMovieFavorite(movieResultsItem, newState);

                }
    }

    void setFavoriteTvShow(){
            Resource<TvResultsItem> tvResource = tvShowDetail.getValue();

            if (tvResource != null){
                TvResultsItem tvResultsItem = tvResource.data;
                final boolean newState = !tvResultsItem.isFavorite();
                movieRepository.setTvFavorite(tvResultsItem, newState);
            }
    }
}
