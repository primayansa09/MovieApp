package com.example.moviecatalogue.ui.Favorite.tvShow;

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

public class FavoriteTvAdapter extends PagedListAdapter<TvResultsItem, FavoriteTvAdapter.FavoriteViewHolder> {

   FavoriteTvAdapter(){
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
                 return oldItem.getId() == newItem.getId();
              }
           };

   @NonNull
   @Override
   public FavoriteTvAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
      ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
      return new FavoriteViewHolder(binding);
   }

   @Override
   public void onBindViewHolder(@NonNull FavoriteTvAdapter.FavoriteViewHolder holder, int i) {
      TvResultsItem model = getItem(i);
      holder.bind(model);
   }


   public class FavoriteViewHolder extends RecyclerView.ViewHolder {

      private final ItemListBinding binding;

      public FavoriteViewHolder(ItemListBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }
      void bind(TvResultsItem model){
         itemView.setOnClickListener(v ->{
            Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MODEL_TV, model.getId());
            itemView.getContext().startActivity(intent);
         });

         Glide.with(itemView.getContext())
                 .load(Const.BASE_IMAGE_URL + model.getPosterPath())
                 .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                         .error(R.drawable.ic_error))
                 .into(binding.imgMovie);
      }
   }

   public TvResultsItem getSwipedData(int swipedPosition){
      return getItem(swipedPosition);
   }
}
