package com.pabcalvid.proyectofinalapi.data.local

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow

class LocalDataSource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)
    private val bookDao = db.bookDao()
    private val characterDao = db.characterDao()
    private val houseDao = db.houseDao() // Agregado para manejar casas

    // 📚 Libros 📚
    fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAll()
    }

    suspend fun insertBook(book: Book) {
        try {
            val result = bookDao.insert(book)
            Log.d("LocalDataSource", "Insert book result: $result")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error inserting book", e)
        }
    }

    suspend fun deleteBook(book: Book) {
        try {
            bookDao.delete(book)
            Log.d("LocalDataSource", "Book deleted")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error deleting book", e)
        }
    }

    // 🧙‍♂️ Personajes 🧙‍♂️
    fun getAllCharacters(): Flow<List<Character>> {
        return characterDao.getAll()
    }

    suspend fun insertCharacter(character: Character) {
        try {
            val result = characterDao.insert(character)
            Log.d("LocalDataSource", "Insert character result: $result")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error inserting character", e)
        }
    }

    suspend fun deleteCharacter(character: Character) {
        try {
            characterDao.delete(character)
            Log.d("LocalDataSource", "Character deleted")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error deleting character", e)
        }
    }

    // 🏰 Casas 🏰
    fun getAllHouses(): Flow<List<House>> {
        return houseDao.getAll()
    }

    suspend fun insertHouse(house: House) {
        try {
            val result = houseDao.insert(house)
            Log.d("LocalDataSource", "Insert house result: $result")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error inserting house", e)
        }
    }

    suspend fun deleteHouse(house: House) {
        try {
            houseDao.delete(house)
            Log.d("LocalDataSource", "House deleted")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error deleting house", e)
        }
    }
}