package com.example.cryptocurrencyapp.fragment.detailsFragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cryptocurrencyapp.InternetConnectionCheck
import com.example.cryptocurrencyapp.MainActivity
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.FragmentDetailsBinding
import com.example.cryptocurrencyapp.fragment.watchListFragment.WatchListViewModel
import com.example.cryptocurrencyapp.fragment.watchListFragment.WatchListViewModelFactory
import com.example.data.API.ApiUtilities
import com.example.data.repository.MarketDataRepositoryImpl
import com.example.data.storage.SharedPrefStorage
import com.example.domain.model.CryptoCurrencyData
import com.example.domain.useCases.GetWatchListUseCase
import com.example.domain.useCases.ReadWatchListUseCase
import com.example.domain.useCases.StoreWatchListUseCase
import com.google.android.material.snackbar.Snackbar


class DetailsFragment : Fragment() {

     private lateinit var binding: FragmentDetailsBinding

     private lateinit var viewModel: DetailsViewModel

     private val item: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, DetailsViewModelFactory(requireContext()))
            .get(DetailsViewModel::class.java)

        val data : CryptoCurrencyData? = item.data


        if (data != null) {
            setUpDetails(data)

            loadChart(data)

            setButtonOnClick(data)

            getDetailsList(data)

            addToWatchList(data)



        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val internetStatus = InternetConnectionCheck(requireContext()).internet_connection()
        if (!internetStatus){
            val snackbar: Snackbar = Snackbar.make(requireView(),"No Internet Connection, please check your connection", Snackbar.LENGTH_SHORT)
            snackbar.setActionTextColor(Color.BLACK)
                .show()
        }

    }

    //private var watchList: ArrayList<String>? = null
    private var watchListIsChecked = false

    private fun addToWatchList(
        data: CryptoCurrencyData
    ) {
        viewModel.readWatchList()

        val watchList = viewModel.getWatchList()

        watchListIsChecked = if (watchList.contains(data.symbol)){
            binding.addWatchlistButton.setImageResource(R.drawable.ic_star)
            true
        }
        else{
            binding.addWatchlistButton.setImageResource(R.drawable.ic_star_outline)
            false
        }

        binding.addWatchlistButton.setOnClickListener{
            watchListIsChecked =
                if (!watchListIsChecked){
                    if (!watchList.contains(data.symbol)){

                        watchList.add(data.symbol)
                    }
                    viewModel.storeWatchList()
                    binding.addWatchlistButton.setImageResource(R.drawable.ic_star)


                    true
                }
            else{
                    binding.addWatchlistButton.setImageResource(R.drawable.ic_star_outline)
                    watchList.remove(data.symbol)
                    viewModel.storeWatchList()

                false
                }
        }

        binding.backStackButton.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }
    }

    private fun setButtonOnClick(item: CryptoCurrencyData) {
        val oneMonth = binding.button
        val oneWeek = binding.button1
        val oneDay = binding.button2
        val fourHour = binding.button3
        val oneHour = binding.button4
        val fifteenMinute = binding.button5

        val clickListener = View.OnClickListener {
            when(it.id){
                fifteenMinute.id -> loadChartData(it, "15", item, oneDay, oneMonth, oneWeek, fourHour, oneHour)
                oneHour.id -> loadChartData(it, "1H", item, oneDay, oneMonth, oneWeek, fourHour, fifteenMinute)
                fourHour.id -> loadChartData(it, "4H", item, oneDay, oneMonth, oneWeek, fifteenMinute, oneHour)
                oneDay.id -> loadChartData(it, "D", item, fifteenMinute, oneMonth, oneWeek, fourHour, oneHour)
                oneWeek.id -> loadChartData(it, "W", item, oneDay, oneMonth, fifteenMinute, fourHour, oneHour)
                oneMonth.id -> loadChartData(it, "M", item, oneDay, fifteenMinute, oneWeek, fourHour, oneHour)
            }
        }

        fifteenMinute.setOnClickListener(clickListener)
        oneHour.setOnClickListener(clickListener)
        fourHour.setOnClickListener(clickListener)
        oneDay.setOnClickListener(clickListener)
        oneWeek.setOnClickListener(clickListener)
        oneMonth.setOnClickListener(clickListener)
    }

    private fun loadChartData(
        it: View?,
        s: String,
        item: CryptoCurrencyData,
        oneDay: AppCompatButton,
        oneMonth: AppCompatButton,
        oneWeek: AppCompatButton,
        fourHour: AppCompatButton,
        oneHour: AppCompatButton
    ) {
        disableButton(oneDay, oneMonth, oneWeek, fourHour, oneHour)
        it?.setBackgroundResource(R.drawable.active_button)
        binding.detaillChartWebView.settings.javaScriptEnabled = true
        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        binding.detaillChartWebView.loadUrl(
           "https://s.tradingview.com/widgetembed/?symbol="+item.symbol +"USD&interval="+ s + "&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1" +
                   "&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides=%7B%7D&overrides=%7B%7D&enabled_features=[]" +
                   "&disabled_features=[]&locale=ru&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"

        )
        Log.d("Test", "symbol is " + item.symbol)

    }

    private fun disableButton(
        oneDay: AppCompatButton,
        oneMonth: AppCompatButton,
        oneWeek: AppCompatButton,
        fourHour: AppCompatButton,
        oneHour: AppCompatButton
    ) {
        oneDay.background = null
        oneMonth.background = null
        oneWeek.background = null
        fourHour.background = null
        oneHour.background = null
    }

    private fun loadChart(item: CryptoCurrencyData) {
        binding.detaillChartWebView.settings.javaScriptEnabled = true
        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        binding.detaillChartWebView.loadUrl(
            "https://s.tradingview.com/widgetembed/?symbol="+item.symbol +"USD&interval=1D&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]" +
                    "&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides=%7B%7D&overrides=%7B%7D&enabled_features=[]&disabled_features=[]" +
                    "&locale=ru&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"

        )
    }

    private fun setUpDetails(data: CryptoCurrencyData) {
        binding.detailSymbolTextView.text = data.symbol

        Glide.with(requireContext()).load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + data.id + ".png")
            .thumbnail(Glide.with(requireContext()).load(R.drawable.spinner))
            .into(binding.detailImageView)

        binding.detailPriceTextView.text = String.format("$%.02f", data.price)

        if (data.percentChange24h >0){

            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.green))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up)
            binding.detailChangeTextView.text = "+ ${String.format("%.02f", data.percentChange24h)}%"
        }
        else{
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.red))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_down)
            binding.detailChangeTextView.text = " ${String.format("%.02f", data.percentChange24h)}%"

        }


    }

    private fun getDetailsList(item : CryptoCurrencyData) {

        binding.detailNameTextView.text = item.name
        binding.detailMarketCapTextView.text = String.format("$%.0002f", item.quoteMarketCap)
        binding.detailVolume.text = String.format("$%.0002f", item.volume24h)
        binding.detailDominance.text = item.quoteDominance.toString()

        if (item.percentChange7d >0){

            binding.detailPercentChange7d.setTextColor(resources.getColor(R.color.green))
            binding.detailPercentChange7d.text = "+ ${String.format("%.02f", item.percentChange7d)}%"
        }
        else{
            binding.detailPercentChange7d.setTextColor(resources.getColor(R.color.red))
            binding.detailPercentChange7d.text = " ${String.format("%.02f", item.percentChange7d)}%"

        }

        if (item.percentChange30d >0){

            binding.detailPercentChange30d.setTextColor(resources.getColor(R.color.green))
            binding.detailPercentChange30d.text = "+ ${String.format("%.02f", item.percentChange30d)}%"
        }
        else{
            binding.detailPercentChange30d.setTextColor(resources.getColor(R.color.red))
            binding.detailPercentChange30d.text = " ${String.format("%.02f", item.percentChange30d)}%"

        }
        binding.detailTotalSupplyTextView.text = item.totalSupply.toString()

    }

}