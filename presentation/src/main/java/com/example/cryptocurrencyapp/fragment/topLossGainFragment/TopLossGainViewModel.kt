package com.example.cryptocurrencyapp.fragment.topLossGainFragment

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.repository.MarketDataRepository
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections
import kotlin.collections.ArrayList

class TopLossGainViewModel(getMarketDataUseCase: GetMarketDataUseCase, repository: MarketDataRepository): ViewModel() {

    //TODO: implement the correct logic

    private val _marketData = MutableLiveData<List<CryptoCurrencyData>?>()
    val marketData: LiveData<List<CryptoCurrencyData>?> = _marketData
    var spinKitVisibility = View.VISIBLE
    val arrayList = MutableLiveData<ArrayList<CryptoCurrencyData>>()

    val getMarketDataUseCase = GetMarketDataUseCase(repository)



    init {
        getMarketData()
        Log.d("vmList",_marketData.value.toString())
    }


    fun getMarketData(){
        viewModelScope.launch {
            val result = getMarketDataUseCase.getMarketData()
            _marketData.value = result

        }
    }

    fun getListwithPos(getMarketDataUseCase: GetMarketDataUseCase, position: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("VM", "${getMarketDataUseCase.getMarketData() != null}")
            if (getMarketDataUseCase.getMarketData() != null) {
                withContext(Dispatchers.Main) {

                    val dataItem = getMarketDataUseCase.getMarketData()

                    Log.d("VM", dataItem.toString())
                    Collections.sort(dataItem) { o1, o2 ->
                        (o2.percentChange24h.toInt())
                            .compareTo(o1.percentChange24h.toInt())
                    }
                    Log.d("VM", dataItem!![0].name)

                    spinKitVisibility = View.GONE

                    if (position == 0) {
                        arrayList.value?.clear()
                        for (i in 0..9) {
                            arrayList.value?.add(dataItem[i])
                            //Log.d("vm", mutableList.value!![i].name)
                        }
                        //Log.d("vm", mutableList.value!![0].name)

                    } else {
                        arrayList.value?.clear()
                        for (i in 0..9) {
                            (dataItem?.get(dataItem.size - 1 - i)
                                ?: null)?.let { arrayList.value?.add(it) }
                        }


                    }
                    //Log.d("VM", "${mutableList.value!![0].name == null}")
                }

            }


        }


    }

}