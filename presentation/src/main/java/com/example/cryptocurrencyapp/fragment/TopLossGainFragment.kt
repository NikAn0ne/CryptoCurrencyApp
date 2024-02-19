package com.example.cryptocurrencyapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrencyapp.adapter.MarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentTopLossGainBinding
import com.example.data.API.ApiInterface
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetMarketDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections


class TopLossGainFragment : Fragment() {

    private lateinit var binding: FragmentTopLossGainBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTopLossGainBinding.inflate(layoutInflater)

        val repository = MarketDataRepositoryImpl(ApiUtilities.api)

        val  getMarketDataUseCase = GetMarketDataUseCase(repository)

        getMarketData(getMarketDataUseCase)


        return binding.root
    }

    private fun getMarketData(getMarketDataUseCase: GetMarketDataUseCase) {

        val position = requireArguments().getInt("position")

        lifecycleScope.launch(Dispatchers.IO) {
            val res = ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()

            if (res.body() != null){
                withContext(Dispatchers.Main){
                    val dataItem = getMarketDataUseCase.getMarketData()

                    Collections.sort(dataItem){
                        o1,o2 -> (o2.percentChange24h.toInt())
                        .compareTo(o1.percentChange24h.toInt())
                    }


                    binding.spinKitView.visibility = GONE
                    val list = ArrayList<CryptoCurrencyData>()

                    if (position == 0){
                        list.clear()
                        for (i in 0..9)
                        {
                            (dataItem?.get(0) ?: null)?.let { list.add(it) }
                        }

                        binding.topGainLoseRecyclerView.adapter = MarketAdapter(
                            requireContext(),
                            list,
                            "home"
                        )
                    }

                    else{
                        list.clear()
                        for (i in 0..9)
                        {
                            (dataItem?.get(dataItem.size - 1 - i) ?: null)?.let { list.add(it) }
                        }

                        binding.topGainLoseRecyclerView.adapter = MarketAdapter(
                            requireContext(),
                            list,
                            "home"
                        )

                    }
                }
            }
        }
    }


}