package com.example.cryptocurrencyapp.fragment.topLossGainFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyapp.adapter.MarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentTopLossGainBinding
import com.example.domain.model.CryptoCurrencyData
import java.util.Collections


class TopLossGainFragment : Fragment() {

    private lateinit var binding: FragmentTopLossGainBinding

    private lateinit var viewModel: TopLossGainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTopLossGainBinding.inflate(layoutInflater)


        viewModel = ViewModelProvider(this,TopLossGainViewModelFactory(requireContext()))
            .get(TopLossGainViewModel::class.java)

        viewModel.marketData.observe(viewLifecycleOwner){
            val position = requireArguments().getInt("position")

                if (it != null){
                        val dataItem = it

                        Collections.sort(dataItem){
                                o1,o2 -> (o2.percentChange24h.toInt())
                            .compareTo(o1.percentChange24h.toInt())
                        }
                        Log.d("nVM", dataItem[0].name)

                        binding.spinKitView.visibility = GONE
                        val list = ArrayList<CryptoCurrencyData>()

                        Log.d("nVM", list.toString())

                        if (position == 0){
                            list.clear()
                            for (i in 0..9)
                            {
                                list.add(dataItem[i])
                                Log.d("nvm", list[i].toString())
                            }
                            Log.d("nVM", list.toString())

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
                            Log.d("VM", list.toString())
                            binding.topGainLoseRecyclerView.adapter = MarketAdapter(
                                requireContext(),
                                list,
                                "home"
                            )

                        }

                }

        }


        return binding.root
    }



}