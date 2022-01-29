package com.example.moviecatalogue.ui.detail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.until.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class DetailViewModelTest {
    private DetailViewModel viewModel;
    private MovieResultsItem dummyMovie = DataDummy.generateDummyMovie().get(0);
    private int movieId = dummyMovie.getId();
    private TvResultsItem dummyTyShow = DataDummy.generateDummyTv().get(0);
    private int tvId = dummyTyShow.getId();


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<MovieResultsItem> movieObserver;

    @Mock
    private Observer<TvResultsItem> tvObserver;

    @Before
    public void setUp(){
       viewModel = new DetailViewModel(movieRepository);
        viewModel.setSelectedMovie(movieId);

    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<MovieResultsItem> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);
        when(movieRepository.getMovieDetail(movieId)).thenReturn(movie);
        MovieResultsItem movieResultsItem = viewModel.getDetailMovie().getValue();
        verify(movieRepository).getMovieDetail(movieId);
        assertNotNull(movieResultsItem);
        assertEquals(dummyMovie.getId(), movieResultsItem.getId());
        assertEquals(dummyMovie.getTitle(), movieResultsItem.getTitle());
        assertEquals(dummyMovie.getBackdropPath(), movieResultsItem.getBackdropPath());
        assertEquals(dummyMovie.getOriginalTitle(), movieResultsItem.getOriginalTitle());
        assertEquals(dummyMovie.getOverview(), movieResultsItem.getOverview());
        assertEquals(dummyMovie.getPosterPath(), movieResultsItem.getPosterPath());
        assertEquals(dummyMovie.getReleaseDate(), movieResultsItem.getReleaseDate());
        assertEquals(dummyMovie.getPopularity(), movieResultsItem.getPopularity(), dummyMovie.getVoteAverage());
        assertEquals(dummyMovie.getVoteAverage(), movieResultsItem.getVoteAverage(), dummyMovie.getVoteAverage());
        assertEquals(dummyMovie.getVoteCount(), movieResultsItem.getVoteCount());

        viewModel.getDetailMovie().observeForever(movieObserver);
        verify(movieObserver).onChanged(dummyMovie);
    }

    @Test
    public void getDetailTvShow(){
        MutableLiveData<TvResultsItem> tv = new MutableLiveData<>();
        tv.setValue(dummyTyShow);
        when(movieRepository.getTvDetail(tvId)).thenReturn(tv);
        TvResultsItem tvResultsItem = viewModel.getDetailTv().getValue();
        verify(movieRepository).getTvDetail(tvId);
        assertNotNull(tvResultsItem);
        assertEquals(dummyTyShow.getId(), tvResultsItem.getId());
        assertEquals(dummyTyShow.getName(), tvResultsItem.getName());
        assertEquals(dummyTyShow.getOriginalName(), tvResultsItem.getOriginalName());
        assertEquals(dummyTyShow.getFirstAirDate(), tvResultsItem.getFirstAirDate());
        assertEquals(dummyTyShow.getOriginalLanguage(), tvResultsItem.getOriginalLanguage());
        assertEquals(dummyTyShow.getOverview(), tvResultsItem.getOverview());
        assertEquals(dummyTyShow.getPosterPath(), tvResultsItem.getPosterPath());
        assertEquals(dummyTyShow.getBackdropPath(), tvResultsItem.getBackdropPath());
        assertEquals(dummyTyShow.getPopularity(), tvResultsItem.getPopularity(), dummyTyShow.getVoteAverage());
        assertEquals(dummyTyShow.getVoteAverage(), tvResultsItem.getVoteAverage(), dummyTyShow.getVoteAverage());
        assertEquals(dummyTyShow.getVoteCount(), tvResultsItem.getVoteCount());

        viewModel.getDetailTv().observeForever(tvObserver);
        verify(tvObserver).onChanged(dummyTyShow);
    }
}