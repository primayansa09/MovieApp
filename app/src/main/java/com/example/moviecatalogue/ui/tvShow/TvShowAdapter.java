package com.example.moviecatalogue.ui.tvShow;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.databinding.ItemListBinding;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.detail.DetailActivity;
import com.example.moviecatalogue.ui.until.Const;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvViewHolder> {

    private ArrayList<TvResultsItem> listTvShow = new ArrayList<>();

    public void setTvModel(List<TvResultsItem> listTv){
        listTvShow.clear();
        listTvShow.addAll(listTv);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TvViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        TvResultsItem tvShowEntity = listTvShow.get(position);
        holder.bind(tvShowEntity);
    }

    @Override
    public int getItemCount() {
        return listTvShow.size();
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
