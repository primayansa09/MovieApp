package com.example.moviecatalogue.ui.tvShow;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.moviecatalogue.ui.data.response.TvResultsItem;
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
public class TvShowViewModelTest {
    private TvShowViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<Resource<PagedList<TvResultsItem>>> observer;

    @Mock
    private PagedList<TvResultsItem> pagedList;

    @Before
    public void setUp(){
       viewModel = new TvShowViewModel(movieRepository);
    }

    @Test
    public void getTvShows() {
        Resource<PagedList<TvResultsItem>> dummyTv = Resource.success(pagedList);
        when(dummyTv.data.size()).thenReturn(2);
        MutableLiveData<Resource<PagedList<TvResultsItem>>> tv = new MutableLiveData<>();
        tv.setValue(dummyTv);

        when(movieRepository.getAllTvShow()).thenReturn(tv);
        List<TvResultsItem> tvEntities = viewModel.getTvItems().getValue().data;
        verify(movieRepository).getAllTvShow();
        assertNotNull(tvEntities);
        assertEquals(2, tvEntities.size());

        viewModel.getTvItems().observeForever(observer);
        verify(observer).onChanged(dummyTv);
    }
}