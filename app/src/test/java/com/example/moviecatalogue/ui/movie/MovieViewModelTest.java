package com.example.moviecatalogue.ui.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.until.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MovieViewModelTest {
    private MovieViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<List<MovieResultsItem>> observer;


    @Before
    public void setUp(){
        viewModel = new MovieViewModel(movieRepository);
    }

    @Test
    public void getMovies() {
        ArrayList<MovieResultsItem> dummyMovie = DataDummy.generateDummyMovie();
        MutableLiveData<List<MovieResultsItem>> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);

        when(movieRepository.getAllMovie()).thenReturn(movie);
        List<MovieResultsItem> movieEntities = viewModel.getMovies().getValue();
        verify(movieRepository).getAllMovie();
        assertNotNull(movieEntities);
        assertEquals(2, movieEntities.size());

        viewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }
}