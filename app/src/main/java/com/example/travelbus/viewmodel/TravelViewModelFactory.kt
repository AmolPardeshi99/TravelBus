package com.example.travelbus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelbus.repository.TravelRepo

class TravelViewModelFactory(val repo: TravelRepo):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TravelViewModel(repo) as T
    }
}