package com.example.cryptocurrencyapp.fragment.watchListFragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyapp.InternetConnectionCheck
import com.example.cryptocurrencyapp.adapter.MarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentWatchListBinding
import com.example.domain.model.CryptoCurrencyData
import com.google.android.material.snackbar.Snackbar

class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding

    private lateinit var viewModel: WatchListViewModel

    private lateinit var watchListItem: ArrayList<CryptoCurrencyData>

    private lateinit var snackbar: Snackbar



    val TAG = "WatchListFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.d(TAG, "WatchListViewCreated")
        // Inflate the layout for this fragment
        binding = FragmentWatchListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, WatchListViewModelFactory(requireContext()))
            .get(WatchListViewModel::class.java)


        viewModel.getLocalWatchList()
        val internetStatus = InternetConnectionCheck(requireContext()).internetConnection()
        if (internetStatus){
            viewModel.marketData.observe(viewLifecycleOwner){
                Log.d(TAG, (it!= null).toString())
                if (it!= null){
                    Log.d(TAG, it[0].name)
                    watchListItem = ArrayList()
                    watchListItem.clear()

                    for (watchData in viewModel.watchList.value!!){
                        Log.d(TAG, "watchList $watchData")
                        for (item in it)
                        {
                            if (watchData.symbol == item.symbol){
                                watchListItem.add(item)
                                Log.d(TAG,"massive ${watchListItem}")
                            }
                        }
                    }

                    Log.d(TAG, watchListItem.size.toString())
                    binding.spinKitView.visibility = GONE
                    binding.watchlistRecyclerView.adapter =
                        MarketAdapter(requireContext(), watchListItem, "watchfragment")


                }
                else{
                    snackbar = Snackbar.make(requireView(),"Your list is empty", Snackbar.LENGTH_SHORT)
                    snackbar.setActionTextColor(Color.BLACK)
                        .show()
                }

            }
        }
        else{
            getLocalWatchList()
        }





        return binding.root
    }


    private fun getLocalWatchList(){
        viewModel.getLocalWatchList()
        if (viewModel.watchList.value!!.size != 0){
            viewModel.watchList.observe(viewLifecycleOwner){
                //Log.d(TAG, (it!= null).toString())
                if (it!= null){
                    Log.d(TAG, it[0].name)
                    watchListItem = ArrayList()
                    watchListItem.clear()

                    for (watchData in it){
                        Log.d(TAG, "watchList $watchData")

                        watchListItem.add(watchData)
                        Log.d(TAG,"massive ${watchListItem}")


                    }

                    Log.d(TAG, watchListItem.size.toString())
                    binding.spinKitView.visibility = GONE
                    binding.watchlistRecyclerView.adapter =
                        MarketAdapter(requireContext(), watchListItem, "watchfragment")


                }

            }
        }


    }




}