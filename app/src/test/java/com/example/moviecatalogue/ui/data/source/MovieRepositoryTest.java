package com.example.moviecatalogue.ui.data.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.example.moviecatalogue.ui.utils.PagedListUtil;
import com.example.moviecatalogue.ui.data.FakeMovieRepository;
import com.example.moviecatalogue.ui.data.remote.RemoteDataSource;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.local.LocalDataSource;
import com.example.moviecatalogue.ui.until.AppExecutors;
import com.example.moviecatalogue.ui.until.DataDummy;
import com.example.moviecatalogue.ui.until.LiveDataTestUtil;
import com.example.moviecatalogue.ui.vo.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;


public class MovieRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private LocalDataSource local = Mockito.mock(LocalDataSource.class);
    private AppExecutors appExecutors = Mockito.mock(AppExecutors.class);
    private FakeMovieRepository movieRepository = new FakeMovieRepository(remote, local, appExecutors);

    private ArrayList<MovieResultsItem> movieResponse = DataDummy.generateDummyMovie();
    private int movieId = movieResponse.get(0).getId();
    private ArrayList<TvResultsItem> tvShowResponse = DataDummy.generateDummyTv();
    private int tvShowId = tvShowResponse.get(0).getId();

    @Test
    public void getAllMovie() {
        DataSource.Factory<Integer, MovieResultsItem> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllMovie()).thenReturn(dataSourceFactory);
        movieRepository.getAllMovie();

        Resource<PagedList<MovieResultsItem>> movieEntites = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()));
        verify(local).getAllMovie();
        assertNotNull(movieEntites.data);
        assertEquals(movieResponse.size(), movieEntites.data.size());
    }

    @Test
    public void getAllTvShow() {
        DataSource.Factory<Integer, TvResultsItem> dataSourceTvFactory = mock(DataSource.Factory.class);
        when(local.getAllTv()).thenReturn(dataSourceTvFactory);
        movieRepository.getAllTvShow();

        Resource<PagedList<TvResultsItem>> tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()));
        verify(local).getAllTv();
        assertNotNull(tvEntities.data);
        assertEquals(tvShowResponse.size(), tvEntities.data.size());
    }

    @Test
    public void getMovieById() {
        MutableLiveData<MovieResultsItem> dummyMovieById = new MutableLiveData<>();
        dummyMovieById.setValue(DataDummy.generateDummyMovie().get(0));
        when(local.getMovieById(movieId)).thenReturn(dummyMovieById);

        MovieResultsItem movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId)).data;
        verify(local).getMovieById(movieId);
        assertNotNull(movieEntity);
        assertEquals(movieResponse.get(0).getTitle(), movieEntity.getTitle());
        assertEquals(movieResponse.get(0).getOriginalTitle(), movieEntity.getOriginalTitle());
        assertEquals(movieResponse.get(0).getOriginalLanguage(), movieEntity.getOriginalLanguage());
        assertEquals(movieResponse.get(0).getReleaseDate(), movieEntity.getReleaseDate());
        assertEquals(movieResponse.get(0).getOverview(), movieEntity.getOverview());
        assertEquals(movieResponse.get(0).getId(), movieEntity.getId());
        assertEquals(movieResponse.get(0).getBackdropPath(), movieEntity.getBackdropPath());
        assertEquals(movieResponse.get(0).getPosterPath(), movieEntity.getPosterPath());
        assertEquals(movieResponse.get(0).getVoteAverage(), movieEntity.getVoteAverage(), movieResponse.get(0).getVoteAverage());
        assertEquals(movieResponse.get(0).getPopularity(), movieEntity.getPopularity(), movieResponse.get(0).getPopularity());
        assertEquals(movieResponse.get(0).getVoteCount(), movieEntity.getVoteCount());
    }

    @Test
    public void getTvById() {
        MutableLiveData<TvResultsItem> dummyTvById = new MutableLiveData<>();
        dummyTvById.setValue(DataDummy.generateDummyTv().get(0));
        when(local.getTvById(tvShowId)).thenReturn(dummyTvById);

        TvResultsItem tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getTvById(tvShowId)).data;
        verify(local).getTvById(tvShowId);
        assertNotNull(tvShowEntity);
        assertEquals(tvShowResponse.get(0).getId(), tvShowEntity.getId());
        assertEquals(tvShowResponse.get(0).getOriginalName(), tvShowEntity.getOriginalName());
        assertEquals(tvShowResponse.get(0).getName(), tvShowEntity.getName());
        assertEquals(tvShowResponse.get(0).getFirstAirDate(), tvShowEntity.getFirstAirDate());
        assertEquals(tvShowResponse.get(0).getOriginalLanguage(), tvShowEntity.getOriginalLanguage());
        assertEquals(tvShowResponse.get(0).getOverview(), tvShowEntity.getOverview());
        assertEquals(tvShowResponse.get(0).getBackdropPath(), tvShowEntity.getBackdropPath());
        assertEquals(tvShowResponse.get(0).getPosterPath(), tvShowEntity.getPosterPath());
        assertEquals(tvShowResponse.get(0).getVoteCount(), tvShowEntity.getVoteCount());
        assertEquals(tvShowResponse.get(0).getPopularity(), tvShowEntity.getPopularity(), tvShowResponse.get(0).getPopularity());
        assertEquals(tvShowResponse.get(0).getVoteAverage(), tvShowEntity.getVoteAverage(), tvShowResponse.get(0).getVoteAverage());
    }

    @Test
    public void setMovieFavorite() {
        DataSource.Factory<Integer, MovieResultsItem> dataMovieFav = mock(DataSource.Factory.class);
        when(local.getFavMovies()).thenReturn(dataMovieFav);
        movieRepository.getMovieFavorite();

        Resource<PagedList<MovieResultsItem>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()));
        verify(local).getFavMovies();
        assertNotNull(movieEntities.data);
        assertEquals(movieResponse.size(), movieEntities.data.size());
    }

    @Test
    public void setTvFavorite() {
        DataSource.Factory<Integer, TvResultsItem> dataTvFav = mock(DataSource.Factory.class);
        when(local.getFavTv()).thenReturn(dataTvFav);
        movieRepository.getTvFavorite();

        Resource<PagedList<TvResultsItem>> tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()));
        verify(local).getFavTv();
        assertNotNull(tvEntities.data);
        assertEquals(movieResponse.size(), tvEntities.data.size());
    }
}