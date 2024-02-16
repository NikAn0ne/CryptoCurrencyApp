package com.example.cryptocurrencyapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.FragmentWatchListBinding


class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWatchListBinding.inflate(layoutInflater)


        return binding.root
    }

}