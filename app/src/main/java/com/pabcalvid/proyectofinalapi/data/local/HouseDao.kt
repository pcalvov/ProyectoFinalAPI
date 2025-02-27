package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseDao {

    @Query("SELECT * FROM houses")
    fun getAll(): Flow<List<House>>

    @Query("SELECT * FROM houses WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<House>>

    @Query("SELECT * FROM houses WHERE house = :house")
    suspend fun getHouseByHouse(house: String): House?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(house: House) // Inserta un personaje

    @Update
    suspend fun update(house: House) // Actualiza un personaje

    @Delete
    suspend fun delete(house: House) // Elimina un personaje

    @Query("UPDATE houses SET isFavorite = :isFavorite WHERE house = :house")
    suspend fun updateFavoriteStatus2(house: String, isFavorite: Boolean)
}
