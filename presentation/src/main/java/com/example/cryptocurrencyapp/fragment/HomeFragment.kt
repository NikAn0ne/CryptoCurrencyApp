package com.example.cryptocurrencyapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.adapter.TopMarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentHomeBinding
import com.example.data.API.ApiInterface
import com.example.data.API.ApiUtilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        getTopCurrencyList()

        return binding.root
    }

    private fun getTopCurrencyList() {
        lifecycleScope.launch(Dispatchers.IO) {
            val res = ApiUtilities.getInstance().create(ApiInterface::class.java)

            withContext(Dispatchers.Main) {
                binding.topCurrencyRecyclerView.adapter = TopMarketAdapter(
                    requireContext(),
                    res.getMarketData().body()!!.data.cryptoCurrencyList
                )
            }


        }

    }


}