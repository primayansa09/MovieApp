package com.example.moviecatalogue.ui.data;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.moviecatalogue.ui.data.remote.ApiResponse;
import com.example.moviecatalogue.ui.data.remote.RemoteDataSource;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.data.source.MovieRepository;
import com.example.moviecatalogue.ui.data.source.MovieTvDataSource;
import com.example.moviecatalogue.ui.local.LocalDataSource;
import com.example.moviecatalogue.ui.until.AppExecutors;
import com.example.moviecatalogue.ui.until.NetworkBoundResource;
import com.example.moviecatalogue.ui.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieRepository implements MovieTvDataSource {

    private volatile static MovieRepository INSTANCE = null;

    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;
    private AppExecutors appExecutors;
    private MovieResultsItem moviesDetail;
    private TvResultsItem tvShowDetail;

    public FakeMovieRepository(RemoteDataSource remote, LocalDataSource local, AppExecutors appExecutors) {
        this.remoteDataSource = remote;
        this.localDataSource = local;
        this.appExecutors = appExecutors;
    }

    public static MovieRepository getInstance(RemoteDataSource remoteData, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                INSTANCE = new MovieRepository(remoteData, localDataSource, appExecutors);
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<PagedList<MovieResultsItem>>> getAllMovie() {
        return new NetworkBoundResource<PagedList<MovieResultsItem>, List<MovieResultsItem>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieResultsItem>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllMovie(), config).build();
            }

            @Override
            protected boolean shouldFetch(PagedList<MovieResultsItem> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResultsItem>>> createCall() {
                return remoteDataSource.findMovies();
            }

            @Override
            protected void saveCallResult(List<MovieResultsItem> data) {
                ArrayList<MovieResultsItem> movieList = new ArrayList<>();
                for (MovieResultsItem response : data) {
                    MovieResultsItem item = new MovieResultsItem(
                            response.getOverview(),
                            response.getOriginalLanguage(),
                            response.getOriginalTitle(),
                            response.getTitle(),
                            response.getPosterPath(),
                            response.getBackdropPath(),
                            response.getReleaseDate(),
                            response.getPopularity(),
                            response.getVoteAverage(),
                            response.getId(),
                            response.getVoteCount());

                    movieList.add(item);
                }

                localDataSource.insertMovies(movieList);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<MovieResultsItem>> getMovieById(int movieId) {
        return new NetworkBoundResource<MovieResultsItem, List<MovieResultsItem>>(appExecutors) {
            @Override
            protected LiveData<MovieResultsItem> loadFromDB() {
                return localDataSource.getMovieById(movieId);
            }

            @Override
            protected boolean shouldFetch(MovieResultsItem data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResultsItem>>> createCall() {
                return remoteDataSource.findMovies();
            }

            @Override
            protected void saveCallResult(List<MovieResultsItem> data) {
                MutableLiveData<MovieResultsItem> detailMovie = new MutableLiveData<>();
                if (data != null){
                    for (MovieResultsItem response : data) {
                        if (movieId == response.getId()) {
                            moviesDetail = new MovieResultsItem(
                                    response.getOverview(),
                                    response.getOriginalLanguage(),
                                    response.getOriginalTitle(),
                                    response.getTitle(),
                                    response.getPosterPath(),
                                    response.getBackdropPath(),
                                    response.getReleaseDate(),
                                    response.getPopularity(),
                                    response.getVoteAverage(),
                                    response.getId(),
                                    response.getVoteCount());
                        }

                        detailMovie.postValue(moviesDetail);
                    }
                }
                localDataSource.updateMovie(moviesDetail);
            }
        }.asLiveData();

    }


    @Override
    public LiveData<Resource<PagedList<TvResultsItem>>> getAllTvShow() {
        return new NetworkBoundResource<PagedList<TvResultsItem>, List<TvResultsItem>>(appExecutors) {
            @Override
            protected LiveData<PagedList<TvResultsItem>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllTv(), config).build();
            }

            @Override
            protected boolean shouldFetch(PagedList<TvResultsItem> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvResultsItem>>> createCall() {
                return remoteDataSource.findTvShow();
            }

            @Override
            protected void saveCallResult(List<TvResultsItem> data) {
                ArrayList<TvResultsItem> tvList = new ArrayList<>();
                for (TvResultsItem response : data){
                    TvResultsItem item = new TvResultsItem(
                            response.getFirstAirDate(),
                            response.getOverview(),
                            response.getOriginalLanguage(),
                            response.getPosterPath(),
                            response.getBackdropPath(),
                            response.getOriginalName(),
                            response.getPopularity(),
                            response.getVoteAverage(),
                            response.getName(),
                            response.getId(),
                            response.getVoteCount());

                    tvList.add(item);
                }

                localDataSource.insertTvShow(tvList);
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<TvResultsItem>> getTvById(int TvId) {
        return new NetworkBoundResource<TvResultsItem, List<TvResultsItem>>(appExecutors) {
            @Override
            protected LiveData<TvResultsItem> loadFromDB() {
                return localDataSource.getTvById(TvId);
            }

            @Override
            protected boolean shouldFetch(TvResultsItem data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<List<TvResultsItem>>> createCall() {
                return remoteDataSource.findTvShow();
            }

            @Override
            protected void saveCallResult(List<TvResultsItem> data) {
                MutableLiveData<TvResultsItem> tvDetail = new MutableLiveData<>();
                if (data != null){
                    for (TvResultsItem response : data){
                        if (TvId == response.getId()){
                            tvShowDetail = new TvResultsItem(
                                    response.getFirstAirDate(),
                                    response.getOverview(),
                                    response.getOriginalLanguage(),
                                    response.getPosterPath(),
                                    response.getBackdropPath(),
                                    response.getOriginalName(),
                                    response.getPopularity(),
                                    response.getVoteAverage(),
                                    response.getName(),
                                    response.getId(),
                                    response.getVoteCount());

                        }
                        tvDetail.postValue(tvShowDetail);
                    }
                }
                localDataSource.updateTvShow(tvShowDetail);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<MovieResultsItem>> getMovieFavorite() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavMovies(), config).build();
    }

    @Override
    public LiveData<PagedList<TvResultsItem>> getTvFavorite() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavTv(), config).build();
    }


    @Override
    public void setMovieFavorite(MovieResultsItem movieResultsItem, boolean newState) {
        appExecutors.diskIO().execute(() -> localDataSource.setMovieFavorite(movieResultsItem, newState));
    }

    @Override
    public void setTvFavorite(TvResultsItem tvResultsItem, boolean newState) {
        appExecutors.diskIO().execute(() -> localDataSource.setTvFavorite(tvResultsItem, newState));
    }
}
