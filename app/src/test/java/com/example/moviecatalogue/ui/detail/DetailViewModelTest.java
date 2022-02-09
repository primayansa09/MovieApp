package com.example.moviecatalogue.ui.detail;
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

        viewModel.movieDetail.observeForever(movieObserver);
        verify(movieObserver).onChanged(movieDetail);
    }

    @Test
    public void getDetailTvShow(){
        Resource<TvResultsItem> tvDetail = Resource.success(dummyTyShow);
        MutableLiveData<Resource<TvResultsItem>> tvShow = new MutableLiveData<>();
        tvShow.setValue(tvDetail);
        when(movieRepository.getTvById(tvId)).thenReturn(tvShow);

        viewModel.tvShowDetail.observeForever(tvObserver);
        verify(tvObserver).onChanged(tvDetail);
    }
}