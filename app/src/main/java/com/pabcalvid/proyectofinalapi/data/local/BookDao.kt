package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAll(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Delete
    fun delete(book: Book)

    @Query("UPDATE books SET isFavorite = :isFavorite WHERE num = :num")
    fun updateFavoriteStatus(num: Int, isFavorite: Boolean)
}