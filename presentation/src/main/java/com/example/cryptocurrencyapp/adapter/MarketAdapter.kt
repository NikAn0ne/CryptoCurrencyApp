package com.example.cryptocurrencyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.CurrencyItemLayoutBinding
import com.example.cryptocurrencyapp.fragment.HomeFragmentDirections
import com.example.cryptocurrencyapp.fragment.MarketFragmentDirections
import com.example.cryptocurrencyapp.fragment.WatchListFragmentDirections
import com.example.domain.model.CryptoCurrencyData

class MarketAdapter(private var context: Context, private var list: List<CryptoCurrencyData>, private var type: String) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    inner  class MarketViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding = CurrencyItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        return MarketViewHolder(LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent , false))
    }

    fun updateData(dataItem: List<CryptoCurrencyData>){
        list = dataItem
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val item = list[position]

        holder.binding.currencyNameTextView.text = item.name
        holder.binding.currencySymbolTextView.text = item.symbol

        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + item.id + ".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.currencyImageView)

        Glide.with(context).load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/" + item.id + ".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.currencyChartImageView)

        holder.binding.currencyPriceTextView.text = "${String.format("$%.02f", item.price)}"


        if (item.percentChange24h >0){

            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.currencyChangeTextView.text = "+ ${String.format("%.02f", item.percentChange24h)}%"
        }
        else{
            holder.binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.currencyChangeTextView.text = " ${String.format("%.02f", item.percentChange24h)}%"

        }

        holder.itemView.setOnClickListener {

            if (type == "home") {
                findNavController(it).navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment().setData(item)
                )
            }
            else if (type == "market"){
                findNavController(it).navigate(
                    MarketFragmentDirections.actionMarketFragmentToDetailsFragment().setData(item)
                )
            }
            else{
                findNavController(it).navigate(
                    WatchListFragmentDirections.actionWatchlistFragmentToDetailsFragment().setData(item)
                )
            }
        }

    }
}