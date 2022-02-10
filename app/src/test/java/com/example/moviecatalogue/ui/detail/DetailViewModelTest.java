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
import com.example.moviecatalogue.ui.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    private Observer<Resource<MovieResultsItem>> movieObserver;

    @Mock
    private Observer<Resource<TvResultsItem>> tvObserver;

    @Before
    public void setUp(){
       viewModel = new DetailViewModel(movieRepository);
        viewModel.setSelectedMovie(movieId);
        viewModel.setSelectedTv(tvId);
    }

    @Test
    public void getDetailMovie() {
        Resource<MovieResultsItem> movieDetail = Resource.success(dummyMovie);
        MutableLiveData<Resource<MovieResultsItem>> movies = new MutableLiveData<>();
        movies.setValue(movieDetail);
        when(movieRepository.getMovieById(movieId)).thenReturn(movies);
        assertNotNull(movies);
        if (movieDetail.data != null){
            assertEquals(dummyMovie.getId(), movieDetail.data.getId());
            assertEquals(dummyMovie.getOriginalTitle(), movieDetail.data.getOriginalTitle());
            assertEquals(dummyMovie.getTitle(), movieDetail.data.getTitle());
            assertEquals(dummyMovie.getOriginalLanguage(), movieDetail.data.getOriginalLanguage());
            assertEquals(dummyMovie.getReleaseDate(), movieDetail.data.getReleaseDate());
            assertEquals(dummyMovie.getOverview(), movieDetail.data.getOverview());
            assertEquals(dummyMovie.getVoteAverage(), movieDetail.data.getVoteAverage(), dummyMovie.getVoteAverage());
            assertEquals(dummyMovie.getPopularity(), movieDetail.data.getPopularity(), dummyMovie.getPopularity());
            assertEquals(dummyMovie.getPosterPath(), movieDetail.data.getPosterPath());
            assertEquals(dummyMovie.getBackdropPath(), movieDetail.data.getBackdropPath());
            assertEquals(dummyMovie.getVoteCount(), movieDetail.data.getVoteCount());
        }
        viewModel.movieDetail.observeForever(movieObserver);
        verify(movieObserver).onChanged(movieDetail);
    }

    @Test
    public void getDetailTvShow(){
        Resource<TvResultsItem> tvDetail = Resource.success(dummyTyShow);
        MutableLiveData<Resource<TvResultsItem>> tvShow = new MutableLiveData<>();
        tvShow.setValue(tvDetail);
        when(movieRepository.getTvById(tvId)).thenReturn(tvShow);
        assertNotNull(tvShow);
        if (tvDetail.data != null){
            assertEquals(dummyTyShow.getId(), tvDetail.data.getId());
            assertEquals(dummyTyShow.getOriginalName(), tvDetail.data.getOriginalName());
            assertEquals(dummyTyShow.getName(), tvDetail.data.getName());
            assertEquals(dummyTyShow.getFirstAirDate(), tvDetail.data.getFirstAirDate());
            assertEquals(dummyTyShow.getOriginalLanguage(), tvDetail.data.getOriginalLanguage());
            assertEquals(dummyTyShow.getOverview(), tvDetail.data.getOverview());
            assertEquals(dummyTyShow.getBackdropPath(), tvDetail.data.getBackdropPath());
            assertEquals(dummyTyShow.getPosterPath(), tvDetail.data.getPosterPath());
            assertEquals(dummyTyShow.getVoteAverage(), tvDetail.data.getVoteAverage(), dummyTyShow.getVoteAverage());
            assertEquals(dummyTyShow.getPopularity(), tvDetail.data.getPopularity(), dummyTyShow.getPopularity());
            assertEquals(dummyTyShow.getVoteCount(), tvDetail.data.getVoteCount());
        }
        viewModel.tvShowDetail.observeForever(tvObserver);
        verify(tvObserver).onChanged(tvDetail);
    }
}