package com.example.moviecatalogue.ui.detail;

import static com.example.moviecatalogue.ui.until.Const.BASE_IMAGE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.databinding.ActivityDetailBinding;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.until.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MODEL = "extra_model";
    public static final String EXTRA_MODEL_TV = "extra_model_tv";
    public static final String TAG = "DetailActivity";

    private ActivityDetailBinding activityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(activityDetailBinding.getRoot());

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Movie");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        DetailViewModel viewModel = new ViewModelProvider(this, factory).get(DetailViewModel.class);

        Bundle extra = getIntent().getExtras();
        Log.e(TAG, "Data: " + extra.toString());

            int idData = extra.getInt(EXTRA_MODEL);
            int tvId = extra.getInt(EXTRA_MODEL_TV);
            if (getIntent().hasExtra(EXTRA_MODEL)) {
                activityDetailBinding.progreeBarDetail.setVisibility(View.VISIBLE);
                activityDetailBinding.content.setVisibility(View.VISIBLE);

                viewModel.setSelectedMovie(idData);
                viewModel.getDetailMovie().observe(this, movies -> {
                    activityDetailBinding.progreeBarDetail.setVisibility(View.GONE);
                    activityDetailBinding.content.setVisibility(View.VISIBLE);

                    detailMovie(movies);
                });
            }else if (getIntent().hasExtra(EXTRA_MODEL_TV)){
                activityDetailBinding.progreeBarDetail.setVisibility(View.VISIBLE);
                activityDetailBinding.content.setVisibility(View.VISIBLE);

                viewModel.setSelectedTv(tvId);
                viewModel.getDetailTv().observe(this, tv->{
                    activityDetailBinding.progreeBarDetail.setVisibility(View.GONE);
                    activityDetailBinding.content.setVisibility(View.VISIBLE);

                    detailTvShow(tv);
                });
            }


        activityDetailBinding.toolbarDetail.imgBackDetail.setOnClickListener(v -> finish());

        sahreOnClik();

    }

    private void detailMovie(MovieResultsItem movieResultsItem) {
        activityDetailBinding.detailContent.tvOriginalTitle.setText(movieResultsItem.getOriginalTitle());
        activityDetailBinding.detailContent.tvTitleDetail.setText(movieResultsItem.getTitle());
        activityDetailBinding.detailContent.tvDescDetail.setText(movieResultsItem.getOverview());
        activityDetailBinding.detailContent.tvReleaseDetail.setText(movieResultsItem.getReleaseDate());
        activityDetailBinding.detailContent.tvoriginalLenguage.setText(movieResultsItem.getOriginalLanguage());
        activityDetailBinding.detailContent.rantingBar.setRating(movieResultsItem.getVoteAverage()/2);

        Glide.with(this)
                .load(BASE_IMAGE_URL + movieResultsItem.getPosterPath())
                .transform(new RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                    .error(R.drawable.ic_error))
                .into(activityDetailBinding.detailContent.imgDetail);
    }

    private void detailTvShow(TvResultsItem tvResultsItem) {
        activityDetailBinding.detailContent.tvOriginalTitle.setText(tvResultsItem.getOriginalName());
        activityDetailBinding.detailContent.tvTitleDetail.setText(tvResultsItem.getName());
        activityDetailBinding.detailContent.tvDescDetail.setText(tvResultsItem.getOverview());
        activityDetailBinding.detailContent.tvReleaseDetail.setText(tvResultsItem.getFirstAirDate());
        activityDetailBinding.detailContent.tvoriginalLenguage.setText(tvResultsItem.getOriginalLanguage());
        activityDetailBinding.detailContent.rantingBar.setRating(tvResultsItem.getVoteAverage()/2);

        Glide.with(this)
                .load(BASE_IMAGE_URL + tvResultsItem.getPosterPath())
                .transform(new RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(activityDetailBinding.detailContent.imgDetail);
    }

    private void sahreOnClik() {
        activityDetailBinding.detailContent.imgShare.setOnClickListener(v -> {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT,activityDetailBinding.detailContent.tvTitleDetail.getText().toString() +"\n"+
                    activityDetailBinding.detailContent.tvReleaseDetail.getText().toString() +"\n"+ activityDetailBinding.detailContent.tvoriginalLenguage.getText().toString()+"\n"+
                    activityDetailBinding.detailContent.tvDescDetail.getText().toString() +"\n"+ activityDetailBinding.detailContent.rantingBar.getRating());
            share.setType("text/plain");

            Intent shareIntent = Intent.createChooser(share, "Bagikan Film ini Sekarang");
            startActivity(shareIntent);
        });
    }
}