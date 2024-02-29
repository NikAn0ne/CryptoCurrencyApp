package com.example.cryptocurrencyapp.fragment.homeFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.data.storage.SharedPrefStorage
import com.example.domain.useCases.GetMarketDataUseCase

class HomeViewModelFactory(context: Context) : ViewModelProvider.Factory {


    private val  watchListStorage by lazy {SharedPrefStorage(context)}

    private val repository by lazy {MarketDataRepositoryImpl(ApiUtilities.api,watchListStorage)}

    private val getMarketDataUseCase by lazy { GetMarketDataUseCase(repository)}


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getMarketDataUseCase = getMarketDataUseCase) as T
    }
}