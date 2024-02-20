package com.example.cryptocurrencyapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrencyapp.adapter.MarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentWatchListBinding
import com.example.data.API.ApiInterface
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.data.storage.SharedPrefStorage
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetMarketDataUseCase
import com.example.domain.useCases.WatchListUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private lateinit var  getMarketDataUseCase : GetMarketDataUseCase

class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding
    //private lateinit var watchList: ArrayList<String>
    private lateinit var watchListItem: ArrayList<CryptoCurrencyData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWatchListBinding.inflate(layoutInflater)

        val  watchListStorage = SharedPrefStorage(requireContext())

        val repository = MarketDataRepositoryImpl(ApiUtilities.api, watchListStorage)

        val getMarketDataUseCase = GetMarketDataUseCase(repository)

        val watchListUseCase = WatchListUseCase(repository)


        watchListUseCase.readWatchList()

        lifecycleScope.launch(Dispatchers.IO){

            if (getMarketDataUseCase.getMarketData() != null)
            {
                withContext(Dispatchers.Main){
                    watchListItem = ArrayList()
                    watchListItem.clear()

                    for (watchData in watchListUseCase.getWatchList()!!){
                        for (item in getMarketDataUseCase.getMarketData()!!){
                            if (watchData == item.symbol)
                            {
                                watchListItem.add(item)
                            }
                        }
                    }

                    binding.spinKitView.visibility = GONE
                    binding.watchlistRecyclerView.adapter = MarketAdapter(requireContext(),watchListItem, "watchfragment")

                }
            }
            else {
                binding.spinKitView.visibility = VISIBLE
            }
        }

        return binding.root
    }


}