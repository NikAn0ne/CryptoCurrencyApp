package com.example.cryptocurrencyapp.fragment.watchListFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.data.storage.SharedPrefStorage
import com.example.domain.useCases.GetMarketDataUseCase
import com.example.domain.useCases.GetWatchListUseCase
import com.example.domain.useCases.ReadWatchListUseCase

class WatchListViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val  watchListStorage by lazy {SharedPrefStorage(context)}

    private val repository by lazy {MarketDataRepositoryImpl(ApiUtilities.api, watchListStorage)}

    private val getMarketDataUseCase by lazy {GetMarketDataUseCase(repository)}

    private val readWatchListUseCase by lazy {ReadWatchListUseCase(repository)}

    private val getWatchListUseCase by lazy {GetWatchListUseCase(repository)}
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WatchListViewModel(getWatchListUseCase,getMarketDataUseCase,readWatchListUseCase,repository,watchListStorage) as T
    }
}