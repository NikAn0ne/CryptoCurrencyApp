package com.example.cryptocurrencyapp.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrencyapp.adapter.MarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentMarketBinding
import com.example.data.API.ApiInterface
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale


class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding

    private lateinit var list: List<CryptoCurrencyData>
    private lateinit var adapter: MarketAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMarketBinding.inflate(layoutInflater)

        val repository = MarketDataRepositoryImpl(ApiUtilities.api)

        val  getMarketDataUseCase = GetMarketDataUseCase(repository)

        list = listOf()

        adapter = MarketAdapter(requireContext(), list, "market")
        binding.currencyRecyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO){
            val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()

            if(res.body() != null)
            {
                withContext(Dispatchers.Main){
                    list = getMarketDataUseCase.getMarketData()!!

                    adapter.updateData(list)
                    binding.spinKitView.visibility = GONE
                }
            }
        }

        searchCoin()

        return binding.root
    }

    lateinit var searchText: String

    private fun searchCoin(){
        binding.searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("TEXT", "CLICk")
                searchText = s.toString().lowercase()

                updateRecyclerView()
            }
        })
    }

    private fun updateRecyclerView(){
        val data = ArrayList<CryptoCurrencyData>()

        for (item in list){
            val coinName = item.name.lowercase(Locale.getDefault())
            val coinSymbol = item.symbol.lowercase(Locale.getDefault())

            if (coinName.contains(searchText) || coinSymbol.contains(searchText)){
                data.add(item)
            }
        }

        adapter.updateData(data)
    }


}