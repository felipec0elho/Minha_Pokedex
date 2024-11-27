package com.example.minhapokedex

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.minhapokedex.data.FavCard
import com.example.minhapokedex.model.FavCardDAO

@Database(entities = [FavCard::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favCardDAO() : FavCardDAO

}