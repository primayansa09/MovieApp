package com.example.moviecatalogue.ui.Favorite.movie;

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
import com.example.moviecatalogue.databinding.FragmentFavoriteMovieBinding;
import com.example.moviecatalogue.ui.Favorite.FavoriteViewModel;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.until.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

public class FavoriteMovieFragment extends Fragment {
    
    private FragmentFavoriteMovieBinding binding;
    private FavoriteMoviesAdapter favAdapter;
    private FavoriteViewModel viewModel;

    public FavoriteMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteMovieBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(FavoriteViewModel.class);
            favAdapter = new FavoriteMoviesAdapter();

            binding.progressBarMovieFav.setVisibility(View.VISIBLE);
            viewModel.getFavoriteMovies().observe(this, movies->{

                    binding.progressBarMovieFav.setVisibility(View.GONE);
                    favAdapter.submitList(movies);

            });
        }

        RecyclerviewFav();
    }


    private void RecyclerviewFav() {

            binding.rvMovieFav.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.rvMovieFav.setHasFixedSize(true);
            binding.rvMovieFav.setAdapter(favAdapter);

            itemTouchHelper.attachToRecyclerView(binding.rvMovieFav);

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
                MovieResultsItem movieResultsItem = favAdapter.getSwipedData(swipedPosition);
                viewModel.setFavoriteData(movieResultsItem);
                Snackbar snackbar = Snackbar.make(getView(), R.string.undo_delete, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.message_ok, v -> viewModel.setFavoriteData(movieResultsItem));
                snackbar.show();
            }
        }
    });
}