package com.example.data.storage

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val SHARED_PREFS_KEY = "watchlist"

class SharedPrefStorage(context: Context) : WatchListStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
    private var watchList: ArrayList<String>? = null
    override fun saveData() {

        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(watchList)
        editor.putString(SHARED_PREFS_KEY, json)
        editor.apply()
    }

    override fun readData() {
        val gson = Gson()
        val json = sharedPreferences.getString(SHARED_PREFS_KEY, ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>(){}.type
        watchList = gson.fromJson(json, type)
        Log.d("SharedPref", "$watchList")
    }

    override fun getWatchList() : ArrayList<String>? {
        return watchList
    }
}