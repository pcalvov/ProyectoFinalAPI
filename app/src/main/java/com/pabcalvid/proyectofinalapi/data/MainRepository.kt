package com.pabcalvid.proyectofinalapi.data

import android.util.Log
import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.data.local.LocalDataSource
import com.pabcalvid.proyectofinalapi.data.remote.RemoteDataSource
import com.pabcalvid.proyectofinalapi.data.remote.toLocalEntity
import kotlinx.coroutines.flow.Flow

class MainRepository(private val localds: LocalDataSource, private val remoteds: RemoteDataSource) {

    //Devuelve todos los libros online
    suspend fun getBooks(): List<Book> {
        val bookDTOList = remoteds.getBooks()
        println("📢 API Response: $bookDTOList") // Log para ver qué llega de la API

        val bookList = bookDTOList.map { it.toLocalEntity() }
        println("📢 Converted Books: $bookList") // Log después de la conversión

        return bookList
    }

    //Devuelve un libro aleatorio online
    suspend fun getRandomBook(): Book {
        return remoteds.getRandomBook().toLocalEntity()
    }

    /**
     * Devuelve todos los libros de la base de datos local
     */
    suspend fun getLocalBooks(): Flow<List<Book>> {
        return localds.getAll()
    }

    /**
     * Inserta un libro en la base de datos local
     */
    fun insertLocalBook(book: Book) {
        localds.insert(book)
    }

    /**
     * Elimina un libro de la base de datos local
     */
    fun deleteLocalBook(book: Book) {
        localds.delete(book)
    }
}