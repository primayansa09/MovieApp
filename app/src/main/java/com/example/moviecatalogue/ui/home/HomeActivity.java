package com.example.moviecatalogue.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.databinding.ActivityHomeBinding;
import com.example.moviecatalogue.ui.setting.SettingActivity;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        binding.homeViewPager.setAdapter(sectionsPagerAdapter);
        binding.tabLayoutHome.setupWithViewPager(binding.homeViewPager);

        binding.tollbar.imgSetting.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(intent);
        });

    }
}