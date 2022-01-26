package com.example.moviecatalogue.ui.movie;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

   private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<List<MovieResultsItem>> getMovies(){
        return movieRepository.getAllMovie();
    }
}
