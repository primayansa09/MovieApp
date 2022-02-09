package com.example.moviecatalogue.ui.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import java.util.List;

@Dao
public interface MovieTvDao {

    @Query("SELECT * FROM movieentities")
    DataSource.Factory<Integer, MovieResultsItem> getMovies();

    @Query("SELECT * FROM tventities")
    DataSource.Factory<Integer, TvResultsItem> getTvShows();

    @Query("SELECT * FROM movieentities where isFavorite = 1")
    DataSource.Factory<Integer, MovieResultsItem> getMovieFavorites();

    @Query("SELECT * FROM tventities where isFavorite = 1")
    DataSource.Factory<Integer, TvResultsItem> getTvFavorites();

    @Transaction
    @Query("SELECT * FROM movieentities where movieId = :movieId")
    LiveData<MovieResultsItem> getMovieById(int movieId);

    @Transaction
    @Query("SELECT * FROM tventities where tvId = :tvId")
    LiveData<TvResultsItem> getTvById(int tvId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(List<MovieResultsItem> movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTv(List<TvResultsItem> tv);

    @Update
    void updateMovie(MovieResultsItem movies);

    @Update
    void updateTv(TvResultsItem tv);
}
