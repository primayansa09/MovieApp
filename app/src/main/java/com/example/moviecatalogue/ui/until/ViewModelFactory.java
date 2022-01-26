package com.example.moviecatalogue.ui.until;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.detail.DetailViewModel;
import com.example.moviecatalogue.ui.movie.MovieViewModel;
import com.example.moviecatalogue.ui.tvShow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MovieRepository mMovieRepository;

    public ViewModelFactory(MovieRepository mMovieRepository) {
        this.mMovieRepository = mMovieRepository;
    }

    public static ViewModelFactory getInstance(Context context){
        if (INSTANCE == null){
            synchronized (ViewModelFactory.class){
                INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if (modelClass.isAssignableFrom(MovieViewModel.class)){
            return (T) new MovieViewModel(mMovieRepository);
        }else if (modelClass.isAssignableFrom(TvShowViewModel.class)){
            return (T) new TvShowViewModel(mMovieRepository);
        }else if (modelClass.isAssignableFrom(DetailViewModel.class)){
            return (T) new DetailViewModel(mMovieRepository);
        }

        throw new IllegalArgumentException("Unknow ViewModel class: " + modelClass.getName());
    }
}
