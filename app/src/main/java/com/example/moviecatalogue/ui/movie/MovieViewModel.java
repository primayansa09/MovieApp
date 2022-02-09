package com.example.moviecatalogue.ui.movie;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.vo.Resource;

public class MovieViewModel extends ViewModel {

   private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<Resource<PagedList<MovieResultsItem>>> getMovies(){
        return movieRepository.getAllMovie();
    }
}
