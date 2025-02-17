package com.pabcalvid.proyectofinalapi.data

import android.util.Log
import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.data.local.LocalDataSource
import com.pabcalvid.proyectofinalapi.data.remote.RemoteDataSource
import com.pabcalvid.proyectofinalapi.data.remote.toLocalEntity
import kotlinx.coroutines.flow.Flow

class MainRepository(private val localds: LocalDataSource, private val remoteds: RemoteDataSource) {

    /**
     * Devuelve el último libro publicado online
     */
    suspend fun getLastBook(): Book {
        return remoteds.getLastBook().toLocalEntity()
    }

    /**
     * Devuelve el libro online indicado por el número
     */
    suspend fun getBookNum(num: Int): Book {
        return remoteds.getBook(num).toLocalEntity()
    }

    /**
     * Devuelve un libro online aleatorio entre el cero y el último publicado
     */
    suspend fun getRandomBook(): Book {
        val num = (0..remoteds.getLastBook().toLocalEntity().num).random()
        Log.d("REPO", num.toString())
        return remoteds.getBook(num).toLocalEntity()
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