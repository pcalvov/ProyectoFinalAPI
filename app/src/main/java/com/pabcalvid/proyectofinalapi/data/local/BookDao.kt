package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAll(): Flow<List<Book>> // Obtiene todos los libros en tiempo real

    @Query("SELECT * FROM books WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Book>> // Obtiene solo los favoritos

    @Query("SELECT * FROM books WHERE num = :num LIMIT 1")
    suspend fun getBookByNum(num: Int): Book? // Devuelve un solo libro (sin Flow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book): Long // Retorna el ID insertado

    @Update
    suspend fun update(book: Book) // Actualiza un libro completo

    @Delete
    suspend fun delete(book: Book) // Borra un libro específico

    @Query("UPDATE books SET isFavorite = :isFavorite WHERE num = :num")
    suspend fun updateFavoriteStatus(num: Int, isFavorite: Boolean) // Alternativa si solo se actualiza el favorito
}