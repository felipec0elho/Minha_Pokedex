package com.example.minhapokedex

import android.app.Application
import androidx.room.Room

class PokeApplication : Application() {

    lateinit var database : AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java
            , "favorites")
            .build()
    }

    companion object {
        lateinit var instance: PokeApplication
            private set
    }
}