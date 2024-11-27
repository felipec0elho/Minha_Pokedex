package com.example.minhapokedex.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favorites")

data class FavCard(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String
)
