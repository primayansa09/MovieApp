package com.example.moviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;

public class DetailViewModel extends ViewModel {
    private int dataId;
    private int dataTv;

    private MovieRepository movieRepository;

    public void setSelectedMovie(int dataId){
        this.dataId = dataId;
    }

    public void setSelectedTv(int dataId){
        this.dataTv = dataId;
    }

    public DetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<MovieResultsItem> getDetailMovie(){
        return movieRepository.getMovieDetail(dataId);
    }

    public LiveData<TvResultsItem> getDetailTv(){
        return movieRepository.getTvDetail(dataTv);
    }


}
