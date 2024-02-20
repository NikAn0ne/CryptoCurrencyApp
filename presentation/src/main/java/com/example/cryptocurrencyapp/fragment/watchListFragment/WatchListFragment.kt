package com.example.cryptocurrencyapp.fragment.watchListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrencyapp.adapter.MarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentWatchListBinding
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.data.storage.SharedPrefStorage
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetMarketDataUseCase
import com.example.domain.useCases.GetWatchListUseCase
import com.example.domain.useCases.ReadWatchListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        val readWatchListUseCase = ReadWatchListUseCase(repository)

        val getWatchListUseCase = GetWatchListUseCase(repository)

        readWatchListUseCase.readWatchList()

        val watchList = getWatchListUseCase.getWatchList()

        lifecycleScope.launch(Dispatchers.IO){

            if (getMarketDataUseCase.getMarketData() != null)
            {
                withContext(Dispatchers.Main) {
                    watchListItem = ArrayList()
                    watchListItem.clear()

                    for (watchData in watchList!!) {
                        for (item in getMarketDataUseCase.getMarketData()!!) {
                            if (watchData == item.symbol) {
                                watchListItem.add(item)
                            }
                        }
                    }

                    binding.spinKitView.visibility = View.GONE
                    binding.watchlistRecyclerView.adapter =
                        MarketAdapter(requireContext(), watchListItem, "watchfragment")

                }
            }
            else {
                binding.spinKitView.visibility = View.VISIBLE
            }
        }

        return binding.root
    }


}