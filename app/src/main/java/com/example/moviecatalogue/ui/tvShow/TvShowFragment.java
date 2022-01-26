package com.example.moviecatalogue.ui.tvShow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.moviecatalogue.databinding.FragmentTvShowBinding;
import com.example.moviecatalogue.ui.until.ViewModelFactory;

public class TvShowFragment extends Fragment {

    private FragmentTvShowBinding fragmentTvShowBinding;
    private TvShowAdapter tvAdapter;
    private TvShowViewModel viewModel;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false);
        return fragmentTvShowBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            fragmentTvShowBinding.progreesBarTvshow.setVisibility(View.VISIBLE);
            viewModel = new ViewModelProvider(this, factory).get(TvShowViewModel.class);
            tvAdapter = new TvShowAdapter();

            viewModel.getTvItems().observe(this, tvShow -> {
                if (tvShow != null) {
                    fragmentTvShowBinding.progreesBarTvshow.setVisibility(View.GONE);
                    tvAdapter.setTvModel(tvShow);
                }
            });

            listRecyclerView();
        }

    }

    private void listRecyclerView() {
        fragmentTvShowBinding.rvTvShow.setLayoutManager(new GridLayoutManager(getContext(), 2));
        fragmentTvShowBinding.rvTvShow.setHasFixedSize(true);
        fragmentTvShowBinding.rvTvShow.setAdapter(tvAdapter);

    }
}