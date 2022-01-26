package com.example.moviecatalogue.ui.detail;

import com.example.moviecatalogue.ui.data.MovieEntity;
import com.example.moviecatalogue.ui.until.DataDummy;

import org.junit.Before;
import org.junit.Test;

public class DetailViewModelTest {
    private DetailViewModel viewModel;
    private MovieEntity dummyMovie = DataDummy.generateDummyMovie().get(0);
    private MovieEntity dummyTyShow = DataDummy.generateDummyTv().get(0);
    private String movieTitle = dummyMovie.getTitle();
    private String tvTitle = dummyTyShow.getTitle();

    @Before
    public void setUp(){
       // viewModel = new DetailViewModel(movieRepository);
       // viewModel.setSelectedMovie(movieTitle);
        //viewModel.setSelectedMovie(tvTitle);
    }

    @Test
    public void getMovie() {
       // viewModel.setSelectedMovie(dummyMovie.getTitle());
        //MovieEntity movieEntity = viewModel.getMovies();
        //assertNotNull(movieEntity);
        //assertEquals(dummyMovie.getId(), movieEntity.getId());
        //assertEquals(dummyMovie.getTitle(), movieEntity.getTitle());
        //assertEquals(dummyMovie.getReleaseDate(), movieEntity.getReleaseDate());
        //assertEquals(dummyMovie.getDuration(), movieEntity.getDuration());
        //assertEquals(dummyMovie.getCategory(), movieEntity.getCategory());
        //assertEquals(dummyMovie.getOverview(), movieEntity.getOverview());
        //assertEquals(dummyMovie.getPosterPath(), movieEntity.getPosterPath());
    }

    @Test
    public void getTvShow(){
       // viewModel.setSelectedMovie(dummyTyShow.getTitle());
        //MovieEntity tvEntity = viewModel.getMovies();
        //assertNotNull(tvEntity);
        //assertEquals(dummyTyShow.getId(), tvEntity.getId());
        //assertEquals(dummyTyShow.getTitle(), tvEntity.getTitle());
        //assertEquals(dummyTyShow.getReleaseDate(), tvEntity.getReleaseDate());
        //assertEquals(dummyTyShow.getDuration(), tvEntity.getDuration());
        //assertEquals(dummyTyShow.getCategory(), tvEntity.getCategory());
        //assertEquals(dummyTyShow.getOverview(), tvEntity.getOverview());
        //assertEquals(dummyTyShow.getPosterPath(), tvEntity.getPosterPath());
    }
}