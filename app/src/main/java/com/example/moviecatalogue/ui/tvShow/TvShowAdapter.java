package com.example.moviecatalogue.ui.tvShow;
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
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.detail.DetailActivity;
import com.example.moviecatalogue.ui.until.Const;

public class TvShowAdapter extends PagedListAdapter<TvResultsItem, TvShowAdapter.TvViewHolder> {

    TvShowAdapter(){
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<TvResultsItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvResultsItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvResultsItem oldItem, @NonNull TvResultsItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull TvResultsItem oldItem, @NonNull TvResultsItem newItem) {
                    return oldItem != newItem;
                }
            };

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TvViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int i) {
        TvResultsItem tvShowEntity = getItem(i);
        holder.bind(tvShowEntity);
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {

        private ItemListBinding binding;

        public TvViewHolder(ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TvResultsItem tvShow){
            binding.tvTitleMovie.setText(tvShow.getOriginalName());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MODEL_TV, tvShow.getId());
                itemView.getContext().startActivity(intent);
            });

            Glide.with(itemView.getContext())
                    .load(Const.BASE_IMAGE_URL + tvShow.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(binding.imgMovie);
        }
    }
}
