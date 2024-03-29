package com.example.cryptocurrencyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.TopCurrencyLayoutBinding
import com.example.cryptocurrencyapp.fragment.homeFragment.HomeFragmentDirections
import com.example.domain.model.CryptoCurrencyData

class TopMarketAdapter(var context: Context, var list: List<CryptoCurrencyData>) : RecyclerView.Adapter<TopMarketAdapter.TopMarketViewHolder>() {

    inner class TopMarketViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding = TopCurrencyLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMarketViewHolder {
        return TopMarketViewHolder(LayoutInflater.from(context).inflate(R.layout.top_currency_layout, parent, false))
    }
    fun updateData(dataItem: List<CryptoCurrencyData>){
        list = dataItem
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TopMarketViewHolder, position: Int) {
        val item = list[position]

        holder.binding.topCurrencyNameTextView.text = item.name

        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + item.id + ".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.topCurrencyImageView)


            if (item.percentChange24h >0){

                holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
                holder.binding.topCurrencyChangeTextView.text = "+ ${String.format("%.02f", item.percentChange24h)}%"
            }
        else{
                holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
                holder.binding.topCurrencyChangeTextView.text = " ${String.format("%.02f", item.percentChange24h)}%"

            }

        holder.itemView.setOnClickListener{
            Navigation.findNavController(it).navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment().setData(item)
            )
        }
    }

}