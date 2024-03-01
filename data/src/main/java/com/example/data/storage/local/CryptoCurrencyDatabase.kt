package com.example.data.storage.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CryptoCurrencyEntity::class],
    version = 1
)
abstract class CryptoCurrencyDatabase: RoomDatabase() {
    abstract fun getDao(): CryptoCurrencyDao

    companion object{

        fun initDb(context: Context): CryptoCurrencyDatabase{
            return Room.databaseBuilder(
                context,
                CryptoCurrencyDatabase::class.java,
                "cryptocurrency.db"
            ).fallbackToDestructiveMigration().allowMainThreadQueries()
                .build()
        }
    }
}