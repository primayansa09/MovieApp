package com.example.moviecatalogue.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.databinding.FragmentMovieBinding;
import com.example.moviecatalogue.ui.until.ViewModelFactory;


public class MovieFragment extends Fragment {

    private FragmentMovieBinding fragmentMovieBinding;
    private MovieAdapter movieAdapter;
    private MovieViewModel viewModel;

    public MovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false);
        return fragmentMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(MovieViewModel.class);
            movieAdapter = new MovieAdapter();

            viewModel.getMovies().observe(this, movie -> {
                if (movie != null) {
                    switch (movie.status){
                        case LOADING:
                            fragmentMovieBinding.progressBarMovie.setVisibility(View.VISIBLE);
                            break;
                        case SUCCES:
                            fragmentMovieBinding.progressBarMovie.setVisibility(View.GONE);
                            movieAdapter.submitList(movie.data);
                            movieAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            fragmentMovieBinding.progressBarMovie.setVisibility(View.GONE);
                            Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            });

            listRecyclerView();
        }
    }

    private void listRecyclerView() {
        fragmentMovieBinding.rvMovie.setLayoutManager(new GridLayoutManager(getContext(), 2));
        fragmentMovieBinding.rvMovie.setHasFixedSize(true);
        fragmentMovieBinding.rvMovie.setAdapter(movieAdapter);

    }
}