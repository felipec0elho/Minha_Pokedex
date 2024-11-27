package com.example.minhapokedex.model
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Update
import com.example.minhapokedex.data.FavCard

@Dao

interface FavCardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteCard: FavCard)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavCard>

    @Delete
    suspend fun delete(favoriteCard: FavCard) // Altere para receber um FavCard

    @Update
    suspend fun atualizar(favoriteCard: FavCard)
}