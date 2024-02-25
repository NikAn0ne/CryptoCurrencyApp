package com.example.cryptocurrencyapp.fragment.watchListFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var viewModel: WatchListViewModel

    private lateinit var watchListItem: ArrayList<CryptoCurrencyData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.d("onCreateView", "WatchListViewCreated")
        // Inflate the layout for this fragment
        binding = FragmentWatchListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, WatchListViewModelFactory(requireContext()))
            .get(WatchListViewModel::class.java)



        val watchList = viewModel.getWatchList()
        Log.d("SharedPref", "Fragment $watchList")
                /*val marketData = viewModel.getMarketData()*/

        viewModel.marketData.observe(viewLifecycleOwner){
            if (it != null)
            {

                watchListItem = ArrayList()
                watchListItem.clear()

                for (watchData in watchList) {
                    for (item in it) {
                        if (watchData == item.symbol) {
                            watchListItem.add(item)
                        }
                    }
                }

                binding.spinKitView.visibility = GONE
                binding.watchlistRecyclerView.adapter =
                    MarketAdapter(requireContext(), watchListItem, "watchfragment")

            }
        }





        return binding.root
    }


}