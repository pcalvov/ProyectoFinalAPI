package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAll(): Flow<List<Character>>

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Character>>

    @Query("SELECT * FROM characters WHERE nickname = :nickname")
    suspend fun getCharacterByNickname(nickname: String): Character?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Update
    suspend fun update(character: Character)

    @Delete
    suspend fun delete(character: Character)

    @Query("UPDATE characters SET isFavorite = :isFavorite WHERE nickname = :nickname")
    suspend fun updateFavoriteStatus(nickname: String, isFavorite: Boolean)
}
