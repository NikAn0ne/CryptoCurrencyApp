package com.example.data.storage.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CryptoCurrencyEntity::class],
    version = 1
)
abstract class CryptoCurrencyDatabase: RoomDatabase() {
    abstract fun getDao(): CryptoCurrencyDao
}