package com.example.cryptocurrencyapp.fragment.detailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.repository.MarketDataRepository
import com.example.domain.useCases.GetWatchListUseCase
import com.example.domain.useCases.ReadWatchListUseCase
import com.example.domain.useCases.StoreWatchListUseCase

class DetailsViewModel(repository: MarketDataRepository) : ViewModel(){


   private val readWatchListUseCase = ReadWatchListUseCase(repository)

   private val getWatchListUseCase = GetWatchListUseCase(repository)

   private val storeWatchListUseCase = StoreWatchListUseCase(repository)


    private val _watchList = MutableLiveData<ArrayList<String>>()

     fun readWatchList() {
        readWatchListUseCase.readWatchList()
    }

    fun getWatchList(): ArrayList<String> {
        readWatchList()
        val result = getWatchListUseCase.getWatchList()!!
        _watchList.value = getWatchListUseCase.getWatchList()!!
        return result
    }

    fun storeWatchList(){
        storeWatchListUseCase.storeWatchList()
    }

}