package com.example.moviecatalogue.ui.until;

import android.content.Context;

import com.example.moviecatalogue.ui.data.remote.RemoteDataSource;
import com.example.moviecatalogue.ui.data.source.MovieRepository;

public class Injection {
    public static MovieRepository provideRepository(Context context){

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();

        return MovieRepository.getInstance(remoteDataSource);
    }
}
