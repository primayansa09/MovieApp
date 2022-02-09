package com.example.moviecatalogue.ui.Favorite.tvShow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.databinding.FragmentFavoriteTvBinding;
import com.example.moviecatalogue.ui.Favorite.FavoriteViewModel;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.until.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;


public class FavoriteTvFragment extends Fragment {

    private FragmentFavoriteTvBinding binding;
    private FavoriteTvAdapter favAdapter;
    private FavoriteViewModel viewModel;

    public FavoriteTvFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteTvBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(FavoriteViewModel.class);
            favAdapter = new FavoriteTvAdapter();

            binding.progressBarTvFav.setVisibility(View.VISIBLE);
            viewModel.getFavortieTvShows().observe(this, tvShows->{
                if (tvShows != null){
                    binding.progressBarTvFav.setVisibility(View.GONE);
                    favAdapter.submitList(tvShows);
                }
            });
        }

        RecyclerviewFav();
    }

    private void RecyclerviewFav() {
        binding.rvTvFav.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvTvFav.setHasFixedSize(true);
        binding.rvTvFav.setAdapter(favAdapter);

        itemTouchHelper.attachToRecyclerView(binding.rvTvFav);
    }

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null){
                int swipedPosition = viewHolder.getAdapterPosition();
                TvResultsItem tvResultsItem = favAdapter.getSwipedData(swipedPosition);
                viewModel.setFavoriteDataTv(tvResultsItem);
                Snackbar snackbar = Snackbar.make(getView(), R.string.undo_delete, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.message_ok, v -> viewModel.setFavoriteDataTv(tvResultsItem));
                snackbar.show();
            }
        }
    });
}