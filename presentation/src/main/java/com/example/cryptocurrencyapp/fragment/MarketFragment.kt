package com.example.cryptocurrencyapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.FragmentMarketBinding


class MarketFragment : Fragment() {

    lateinit var binding: FragmentMarketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMarketBinding.inflate(layoutInflater)



        return binding.root
    }


}