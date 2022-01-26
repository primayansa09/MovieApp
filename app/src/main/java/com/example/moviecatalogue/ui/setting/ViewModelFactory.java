package com.example.moviecatalogue.ui.setting;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final SettingPreferences pref;

    public ViewModelFactory(SettingPreferences dataStore){
        this.pref = dataStore;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if (modelClass.isAssignableFrom(SettingViewModel.class)){
            return (T) new SettingViewModel(pref);
        }

        throw new IllegalArgumentException("Unknow ViewModel class:" + modelClass.getName());
    }
}
