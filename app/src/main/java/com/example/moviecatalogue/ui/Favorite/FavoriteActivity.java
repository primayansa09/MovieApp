package com.example.moviecatalogue.ui.Favorite;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FavoriteActivity extends AppCompatActivity {

    private ActivityFavoriteBinding binding;

    @StringRes
    private final int[] TAB_TITLE = new int[]{
            R.string.favorite_movie,
            R.string.favorite_tv
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            FavoritePagerAdapter favoritePagerAdapter = new FavoritePagerAdapter(this);
            ViewPager2 viewPager = findViewById(R.id.FavViewPager);
            viewPager.setAdapter(favoritePagerAdapter);
            TabLayout tabs = findViewById(R.id.tabLayoutFav);
            new TabLayoutMediator(tabs, viewPager,
                    (tab, position) -> tab.setText(getResources().getString(TAB_TITLE[position]))
            ).attach();


        binding.toolbarFavorite.imgBackFavorite.setOnClickListener(v -> finish());
    }
}