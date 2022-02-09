package com.example.moviecatalogue.ui.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MovieViewModelTest {
    private MovieViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<Resource<PagedList<MovieResultsItem>>> observer;

    @Mock
    private PagedList<MovieResultsItem> pagedList;

    @Before
    public void setUp(){
        viewModel = new MovieViewModel(movieRepository);
    }

    @Test
    public void getMovies() {
        Resource<PagedList<MovieResultsItem>> dummyMovie = Resource.success(pagedList);
        when(dummyMovie.data.size()).thenReturn(2);
        MutableLiveData<Resource<PagedList<MovieResultsItem>>> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);

        when(movieRepository.getAllMovie()).thenReturn(movie);
        List<MovieResultsItem> movieEntities = viewModel.getMovies().getValue().data;
        verify(movieRepository).getAllMovie();
        assertNotNull(movieEntities);
        assertEquals(2, movieEntities.size());

        viewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }
}