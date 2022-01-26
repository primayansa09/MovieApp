package com.example.moviecatalogue.ui.data.source;

import androidx.lifecycle.LiveData;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;

import java.util.ArrayList;
import java.util.List;

public interface MovieTvDataSource {

    LiveData<List<MovieResultsItem>> getAllMovie();

    LiveData<List<TvResultsItem>> getAllTvShow();

    LiveData<MovieResultsItem> getMovieDetail(String movieId);

    LiveData<TvResultsItem> getTvDetail(String TvId);
}
