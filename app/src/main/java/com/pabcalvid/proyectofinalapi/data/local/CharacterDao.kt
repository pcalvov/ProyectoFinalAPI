package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAll(): Flow<List<Character>> // Obtiene todos los personajes

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Character>> // Obtiene solo los favoritos

    @Query("SELECT * FROM characters WHERE nickname = :nickname")
    suspend fun getCharacterByNickname(nickname: String): Character? // Obtiene un personaje específico

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character) // Inserta un personaje

    @Update
    suspend fun update(character: Character) // Actualiza un personaje

    @Delete
    suspend fun delete(character: Character) // Elimina un personaje

    @Query("UPDATE characters SET isFavorite = :isFavorite WHERE nickname = :nickname")
    suspend fun updateFavoriteStatus(nickname: String, isFavorite: Boolean) // Actualiza solo el favorito
}
