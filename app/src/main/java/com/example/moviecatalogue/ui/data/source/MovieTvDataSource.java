package com.example.moviecatalogue.ui.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.vo.Resource;

public interface MovieTvDataSource {

    LiveData<Resource<PagedList<MovieResultsItem>>> getAllMovie();

    LiveData<Resource<PagedList<TvResultsItem>>> getAllTvShow();

    LiveData<Resource<MovieResultsItem>> getMovieById(int movieId);

    LiveData<Resource<TvResultsItem>> getTvById(int TvId);

    LiveData<PagedList<MovieResultsItem>> getMovieFavorite();

    LiveData<PagedList<TvResultsItem>> getTvFavorite();

    void setMovieFavorite(MovieResultsItem movieResultsItem, boolean newState);

    void setTvFavorite(TvResultsItem tvResultsItem, boolean newState);
}
