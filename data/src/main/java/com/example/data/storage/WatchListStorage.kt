package com.example.data.storage

interface WatchListStorage {

    fun saveData()

    fun readData()


    fun getWatchList() : ArrayList<String>?

}