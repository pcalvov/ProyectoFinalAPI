package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAll(): Flow<List<Character>>

    @Insert
    suspend fun insert(character: Character)

    @Delete
    suspend fun delete(character: Character)
}
