package com.example.moviecatalogue.ui.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public TvShowViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<List<TvResultsItem>> getTvItems(){
        return movieRepository.getAllTvShow();
    }

}
