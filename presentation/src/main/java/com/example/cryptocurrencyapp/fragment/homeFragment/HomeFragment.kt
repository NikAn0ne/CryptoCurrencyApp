package com.example.cryptocurrencyapp.fragment.homeFragment

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptocurrencyapp.InternetConnectionCheck
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.adapter.TopLossGainPagerAdapter
import com.example.cryptocurrencyapp.adapter.TopMarketAdapter
import com.example.cryptocurrencyapp.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this,
            HomeViewModelFactory(requireContext())).get(HomeViewModel::class.java)







        setTabLayout()

        return binding.root
    }

    //Placing tabLayout and connecting adapter
    private fun setTabLayout() {
        val adapter = TopLossGainPagerAdapter(this)
        binding.contentViewPager.adapter = adapter

        binding.contentViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.topGainIndicator.visibility = VISIBLE
                    binding.topLoseIndicator.visibility = GONE
                } else {
                    binding.topGainIndicator.visibility = GONE
                    binding.topGainIndicator.visibility = VISIBLE
                }
            }
        })

        TabLayoutMediator(binding.tabLayout, binding.contentViewPager) { tab, position ->
            val title = if (position == 0) {
                "Лидеры роста "
            } else {
                "Лидеры падения"
            }
            tab.text = title
        }.attach()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val internetStatus = InternetConnectionCheck(requireContext()).internet_connection()

        if (internetStatus){

            viewModel.list.observe(viewLifecycleOwner){
                if (it != null){

                    val adapter = TopMarketAdapter(requireContext(),it)
                    adapter.updateData(it)
                    binding.topCurrencyRecyclerView.adapter = adapter
                }
            }
        }
        else{

            val snackbar: Snackbar = Snackbar.make(requireView(),"Please check your Internet Connection and relaunch the App", Snackbar.LENGTH_SHORT)
            snackbar.setActionTextColor(Color.BLACK)
            .show()
        }

    }

    /*private fun getTopCurrencyList() {
        viewModel.list.observe(viewLifecycleOwner) {

            binding.topCurrencyRecyclerView.adapter = it.let {
                TopMarketAdapter(requireContext(), it)
            }
        }*/


        /* lifecycleScope.launch(Dispatchers.IO) {
             withContext(Dispatchers.Main) {
                 binding.topCurrencyRecyclerView.adapter =
                     getMarketDataUseCase.getMarketData()?.let {
                         TopMarketAdapter(
                             requireContext(), it
                         )
                     }
             }
         }*/

        /*else{
                val snackbar: Snackbar = Snackbar.make(requireView(),"No Internet Connection", Snackbar.LENGTH_SHORT)

                snackbar.setActionTextColor(Color.BLACK)
                snackbar.setAction(R.string.try_again, View.OnClickListener {
                }).show()
            }
            }*/


         fun internet_connection(): Boolean {
        //Check if connected to internet, output accordingly
        val cm =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }



    }





