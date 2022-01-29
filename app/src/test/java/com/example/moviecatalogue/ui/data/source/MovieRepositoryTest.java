package com.example.moviecatalogue.ui.data.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.moviecatalogue.ui.data.FakeMovieRepository;
import com.example.moviecatalogue.ui.data.remote.RemoteDataSource;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.until.DataDummy;
import com.example.moviecatalogue.ui.until.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MovieRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private FakeMovieRepository movieRepository = new FakeMovieRepository(remote);

    private ArrayList<MovieResultsItem> movieResponse = DataDummy.generateDummyMovie();
    private int movieId = movieResponse.get(0).getId();
    private ArrayList<TvResultsItem> tvShowResponse = DataDummy.generateDummyTv();
    private int tvShowId = tvShowResponse.get(0).getId();

    @Test
    public void getAllMovie() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieCallback) invocation.getArguments()[0])
                    .getMovies(movieResponse);
            return null;
        }).when(remote).findMovies(any(RemoteDataSource.LoadMovieCallback.class));
        List<MovieResultsItem> movieResultsItems = LiveDataTestUtil.getValue(movieRepository.getAllMovie());
        verify(remote).findMovies(any(RemoteDataSource.LoadMovieCallback.class));
        assertNotNull(movieResultsItems);
        assertEquals(movieResponse.size(), movieResultsItems.size());
    }

    @Test
    public void getMovieDetail() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMovieCallback) invocation.getArguments()[0])
                    .getMovies(movieResponse);
            return null;
        }).when(remote).findMovies(any(RemoteDataSource.LoadMovieCallback.class));
        MovieResultsItem movieResultsItem = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId));
        verify(remote).findMovies(any(RemoteDataSource.LoadMovieCallback.class));
        assertNotNull(movieResultsItem);
        assertEquals(movieResponse.get(0).getOverview(), movieResultsItem.getOverview());
        assertEquals(movieResponse.get(0).getOriginalLanguage(), movieResultsItem.getOriginalLanguage());
        assertEquals(movieResponse.get(0).getOriginalTitle(), movieResultsItem.getOriginalTitle());
        assertEquals(movieResponse.get(0).getTitle(), movieResultsItem.getTitle());
        assertEquals(movieResponse.get(0).getPosterPath(), movieResultsItem.getPosterPath());
        assertEquals(movieResponse.get(0).getBackdropPath(), movieResultsItem.getBackdropPath());
        assertEquals(movieResponse.get(0).getReleaseDate(), movieResultsItem.getReleaseDate());
        assertEquals(movieResponse.get(0).getPopularity(), movieResultsItem.getPopularity(), movieResponse.get(0).getPopularity());
        assertEquals(movieResponse.get(0).getVoteAverage(), movieResultsItem.getVoteAverage(), movieResponse.get(0).getVoteAverage());
        assertEquals(movieResponse.get(0).getId(), movieResultsItem.getId());
        assertEquals(movieResponse.get(0).getVoteCount(), movieResultsItem.getVoteCount());


    }

    @Test
    public void getAllTvShow() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTvCallback) invocation.getArguments()[0])
                    .getTvShow(tvShowResponse);
            return null;
        }).when(remote).findTvShow(any(RemoteDataSource.LoadTvCallback.class));
        List<TvResultsItem> tvResultsItems = LiveDataTestUtil.getValue(movieRepository.getAllTvShow());
        verify(remote).findTvShow(any(RemoteDataSource.LoadTvCallback.class));
        assertNotNull(tvResultsItems);
        assertEquals(movieResponse.size(), tvResultsItems.size());
    }

    @Test
    public void getTvDetail() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTvCallback) invocation.getArguments()[0])
                    .getTvShow(tvShowResponse);
            return null;
        }).when(remote).findTvShow(any(RemoteDataSource.LoadTvCallback.class));
        TvResultsItem tvResultsItems = LiveDataTestUtil.getValue(movieRepository.getTvDetail(tvShowId));
        verify(remote).findTvShow(any(RemoteDataSource.LoadTvCallback.class));
        assertNotNull(tvResultsItems);
        assertEquals(tvShowResponse.get(0).getVoteAverage(), tvResultsItems.getVoteAverage(), movieResponse.get(0).getVoteAverage());
        assertEquals(tvShowResponse.get(0).getPopularity(), tvResultsItems.getPopularity(), movieResponse.get(0).getPopularity());
        assertEquals(tvShowResponse.get(0).getOriginalLanguage(), tvResultsItems.getOriginalLanguage());
        assertEquals(tvShowResponse.get(0).getId(), tvResultsItems.getId());
        assertEquals(tvShowResponse.get(0).getVoteCount(), tvResultsItems.getVoteCount());
        assertEquals(tvShowResponse.get(0).getOverview(), tvResultsItems.getOverview());
        assertEquals(tvShowResponse.get(0).getBackdropPath(), tvResultsItems.getBackdropPath());
        assertEquals(tvShowResponse.get(0).getFirstAirDate(), tvResultsItems.getFirstAirDate());
        assertEquals(tvShowResponse.get(0).getOriginalName(), tvResultsItems.getOriginalName());
        assertEquals(tvShowResponse.get(0).getName(), tvResultsItems.getName());
        assertEquals(tvShowResponse.get(0).getPosterPath(), tvResultsItems.getPosterPath());
    }
}