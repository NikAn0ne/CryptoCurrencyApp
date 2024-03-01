package com.example.cryptocurrencyapp.fragment.marketFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyapp.adapter.MarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentMarketBinding
import com.example.domain.model.CryptoCurrencyData
import java.util.Locale


class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding

    private lateinit var viewModel: MarketViewModel


    private lateinit var list: List<CryptoCurrencyData>
    private lateinit var adapter: MarketAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMarketBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this,MarketViewModelFactory(requireContext()))
            .get(MarketViewModel::class.java)

        list = listOf()

        adapter = MarketAdapter(requireContext(), list, "market")
        binding.currencyRecyclerView.adapter = adapter

                viewModel.list.observe(viewLifecycleOwner){
                    if(it != null) {
                        list = it

                        adapter.updateData(list)
                        binding.spinKitView.visibility = GONE
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