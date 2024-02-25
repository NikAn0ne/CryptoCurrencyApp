package com.example.cryptocurrencyapp.fragment.watchListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.storage.WatchListStorage
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataRepository
import com.example.domain.useCases.GetMarketDataUseCase
import com.example.domain.useCases.GetWatchListUseCase
import com.example.domain.useCases.ReadWatchListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchListViewModel(
    getWatchListUseCase: GetWatchListUseCase,
    getMarketDataUseCase: GetMarketDataUseCase, readWatchListUseCase: ReadWatchListUseCase,
    repository: MarketDataRepository, watchListStorage: WatchListStorage
) : ViewModel() {

    private val _watchListItem = MutableLiveData<ArrayList<CryptoCurrencyData>>()

    //    var watchListItem : LiveData<ArrayList<CryptoCurrencyData>>
    private val _watchList = MutableLiveData<ArrayList<String>>()
    val watchList: LiveData<ArrayList<String>> = _watchList

    private val _marketData: MutableLiveData<List<CryptoCurrencyData>?> = MutableLiveData()
    val marketData: LiveData<List<CryptoCurrencyData>?> = _marketData

    private val getWatchListUseCase = GetWatchListUseCase(repository)
    private val getMarketDataUseCase = GetMarketDataUseCase(repository)
    private val readWatchListUseCase = ReadWatchListUseCase(repository)

    init {
        readWatchList()
        getMarketData()
        getWatchList()

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

    fun getMarketData() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = getMarketDataUseCase.getMarketData()
            _marketData.value = result

        }
    }

    fun observeWatchList(
        readWatchListUseCase: ReadWatchListUseCase, getWatchListUseCase: GetWatchListUseCase,
        getMarketDataUseCase: GetMarketDataUseCase
    ) {
        readWatchListUseCase.readWatchList()
        _watchList.value = getWatchListUseCase.getWatchList()

        viewModelScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {
                _watchListItem.value = ArrayList()
                _watchListItem.value?.clear()

                for (watchData in _watchList.value!!) {
                    for (item in getMarketDataUseCase.getMarketData()!!) {
                        if (watchData == item.symbol) {
                            _watchListItem.value!!.add(item)
                        }
                    }
                }

                /* binding.spinKitView.visibility = View.GONE
                 binding.watchlistRecyclerView.adapter =
                     MarketAdapter(requireContext(), watchListItem, "watchfragment")*/

            }

        }
    }

}
