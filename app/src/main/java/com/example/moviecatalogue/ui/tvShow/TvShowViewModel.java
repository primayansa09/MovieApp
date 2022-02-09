package com.example.moviecatalogue.ui.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.vo.Resource;

public class TvShowViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public TvShowViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<Resource<PagedList<TvResultsItem>>> getTvItems(){
        return movieRepository.getAllTvShow();
    }

}
