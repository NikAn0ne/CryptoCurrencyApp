package com.example.cryptocurrencyapp


import android.content.Context
import android.net.ConnectivityManager


class InternetConnectionCheck (context: Context) {
    private val context = context
    fun internet_connection(): Boolean {
        //Check if connected to internet, output accordingly
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

}