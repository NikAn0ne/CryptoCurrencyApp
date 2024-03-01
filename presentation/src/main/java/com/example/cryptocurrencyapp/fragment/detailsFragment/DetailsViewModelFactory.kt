package com.example.cryptocurrencyapp.fragment.detailsFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.data.storage.SharedPrefStorage
import com.example.data.storage.local.MarketDataLocalRepositoryImpl

class DetailsViewModelFactory(context: Context) : ViewModelProvider.Factory{

    private val  watchListStorage by lazy { SharedPrefStorage(context) }

    private val repository by lazy { MarketDataRepositoryImpl(ApiUtilities.api,watchListStorage) }

    private val localRepository by lazy { MarketDataLocalRepositoryImpl(context) }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(repository, localRepository) as T
    }
}