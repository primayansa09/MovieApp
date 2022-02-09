package com.example.moviecatalogue.ui.movie;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.databinding.ItemListBinding;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.detail.DetailActivity;
import com.example.moviecatalogue.ui.until.Const;

public class MovieAdapter extends PagedListAdapter<MovieResultsItem, MovieAdapter.MovieViewholder> {

    MovieAdapter(){
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<MovieResultsItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieResultsItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieResultsItem oldItem, @NonNull MovieResultsItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull MovieResultsItem oldItem, @NonNull MovieResultsItem newItem) {
                    return oldItem != newItem;
                }
            };

    @NonNull
    @Override
    public MovieViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewholder holder, int i) {
        MovieResultsItem model = getItem(i);
        holder.bind(model);
    }

    public class MovieViewholder extends RecyclerView.ViewHolder {

        private final ItemListBinding binding;

        public MovieViewholder(ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(MovieResultsItem model){
            binding.tvTitleMovie.setText(model.getOriginalTitle());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MODEL, model.getId());
                itemView.getContext().startActivity(intent);
            });

            Glide.with(itemView.getContext())
                    .load(Const.BASE_IMAGE_URL + model.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(binding.imgMovie);
        }
    }
}
