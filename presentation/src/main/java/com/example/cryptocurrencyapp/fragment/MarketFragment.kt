package com.example.cryptocurrencyapp.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.adapter.MarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentMarketBinding
import com.example.data.API.ApiInterface
import com.example.data.API.ApiUtilities
import com.example.domain.model.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MarketFragment : Fragment() {

    lateinit var binding: FragmentMarketBinding

    private lateinit var list: List<CryptoCurrency>
    private lateinit var adapter: MarketAdapter

    lateinit var searchText: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMarketBinding.inflate(layoutInflater)

        list = listOf()

        adapter = MarketAdapter(requireContext(), list)
        binding.currencyRecyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO){
            val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()

            if(res.body() != null)
            {
                withContext(Dispatchers.Main){
                    list = res.body()!!.data.cryptoCurrencyList

                    adapter.updateData(list)
                    binding.spinKitView.visibility = GONE
                }
            }
        }





        return binding.root
    }

    private fun searchCoin(){
        binding.searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                searchText = s.toString().lowercase()

                updateRecyclerView()
            }
        })
    }

    private fun updateRecyclerView(){
        val data = ArrayList<CryptoCurrency>()

        for (item in list){
            val coinName = item.name.lowercase()
            val coinSymbol = item.symbol.lowercase()

            if (coinName.contains(searchText) || coinSymbol.contains(searchText)){
                data.add(item)
            }
        }

        adapter.updateData(data)
    }


}