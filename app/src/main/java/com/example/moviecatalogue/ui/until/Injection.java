package com.example.moviecatalogue.ui.until;

import android.content.Context;

import com.example.moviecatalogue.ui.data.remote.RemoteDataSource;
import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.local.LocalDataSource;
import com.example.moviecatalogue.ui.local.room.MovieDataBase;

public class Injection {
    public static MovieRepository provideRepository(Context context){

        MovieDataBase dataBase = MovieDataBase.getInstance(context);

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        LocalDataSource localDataSource = LocalDataSource.getInstance(dataBase.movieTvDao());
        AppExecutors appExecutors = new AppExecutors();

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
