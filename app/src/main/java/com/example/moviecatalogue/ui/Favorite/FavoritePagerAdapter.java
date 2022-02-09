package com.example.moviecatalogue.ui.Favorite;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.moviecatalogue.ui.Favorite.movie.FavoriteMovieFragment;
import com.example.moviecatalogue.ui.Favorite.tvShow.FavoriteTvFragment;

public class FavoritePagerAdapter extends FragmentStateAdapter {


   public FavoritePagerAdapter(@NonNull AppCompatActivity activity) {
      super(activity);
   }

   @NonNull
   @Override
   public Fragment createFragment(int position) {
      Fragment fragment = null;
      switch (position){
         case 0:
            fragment = new FavoriteMovieFragment();
            break;
         case 1:
            fragment = new FavoriteTvFragment();
            break;
      }
      return fragment;
   }

   @Override
   public int getItemCount() {
      return 2;
   }
}
