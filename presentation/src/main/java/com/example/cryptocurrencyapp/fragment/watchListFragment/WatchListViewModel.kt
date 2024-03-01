package com.example.cryptocurrencyapp.fragment.watchListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.storage.WatchListStorage
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataLocalRepository
import com.example.domain.repository.MarketDataRepository
import com.example.domain.useCases.GetMarketDataUseCase
import com.example.domain.useCases.GetWatchListUseCase
import com.example.domain.useCases.ReadWatchListUseCase
import com.example.domain.useCases.WatchListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchListViewModel(
    repository: MarketDataRepository,localRepository: MarketDataLocalRepository
) : ViewModel() {

    private val _watchListItem = MutableLiveData<ArrayList<CryptoCurrencyData>>()

    private val _watchList = MutableLiveData<ArrayList<String>>()

    val _marketData: MutableLiveData<List<CryptoCurrencyData>> = MutableLiveData<List<CryptoCurrencyData>>()
    val marketData: LiveData<List<CryptoCurrencyData>?> = _marketData

    private val getWatchListUseCase = GetWatchListUseCase(repository)
    private val getMarketDataUseCase = GetMarketDataUseCase(repository)
    private val readWatchListUseCase = ReadWatchListUseCase(repository)
    private val watchListUseCase = WatchListUseCase(localRepository)

    val watchList : MutableLiveData<MutableList<CryptoCurrencyData>> = MutableLiveData<MutableList<CryptoCurrencyData>>()


    init {
        readWatchList()
        getMarketData()
        getWatchList()
        getLocalWatchList()
        //observeWatchList(readWatchListUseCase, getWatchListUseCase,getMarketDataUseCase)

    }

    private fun readWatchList() {
        readWatchListUseCase.readWatchList()
    }

    fun getWatchList(): ArrayList<String> {
        readWatchList()
        val result = getWatchListUseCase.getWatchList()!!
        _watchList.value = getWatchListUseCase.getWatchList()!!
        return result
    }

    fun getMarketData() = viewModelScope.launch(Dispatchers.Main) {
            _marketData.value = getMarketDataUseCase.getMarketData()

    }

    fun getLocalWatchList() = viewModelScope.launch{

            watchList.value = watchListUseCase.getWatchList()


    }

}
