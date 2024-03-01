package com.example.cryptocurrencyapp.fragment.topLossGainFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.data.storage.SharedPrefStorage
import com.example.domain.useCases.GetMarketDataUseCase

class TopLossGainViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val  watchListStorage by lazy { SharedPrefStorage(context) }

    private val repository by lazy { MarketDataRepositoryImpl(ApiUtilities.api,watchListStorage) }



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopLossGainViewModel(repository) as T
    }
}