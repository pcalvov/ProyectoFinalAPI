package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAll(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE num = :num LIMIT 1")
    suspend fun getBookByNum(num: Int): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book): Long

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("UPDATE books SET isFavorite = :isFavorite WHERE num = :num")
    suspend fun updateFavoriteStatus(num: Int, isFavorite: Boolean)
}