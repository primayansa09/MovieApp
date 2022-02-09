package com.example.moviecatalogue.ui.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Setting");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "setting").build();
        SettingPreferences pref = SettingPreferences.getInstance(dataStore);

        SettingViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(pref)).get(SettingViewModel.class);
        viewModel.getThemeSettings().observe(this, isDarkModeactive -> {
            if (isDarkModeactive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                binding.switchThme.setChecked(true);
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                binding.switchThme.setChecked(false);
            }
        });

        binding.toolbarSetting.imgBackSetting.setOnClickListener(v -> {
            finish();
        });

        binding.lytBahasa.setOnClickListener(v -> {
            Intent setting = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(setting);
        });

        binding.switchThme.setOnCheckedChangeListener((buttonView, isChecked) -> {
                viewModel.saveThemeSetting(isChecked);
        });
    }
}