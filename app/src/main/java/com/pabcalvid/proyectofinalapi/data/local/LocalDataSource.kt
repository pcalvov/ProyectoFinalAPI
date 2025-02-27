package com.pabcalvid.proyectofinalapi.data.local

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(applicationContext: Context) {

    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)
    private val bookDao = db.bookDao()
    private val characterDao = db.characterDao()
    private val houseDao = db.houseDao()

    //Libros
    fun getAllBooks(): Flow<List<Book>> = bookDao.getAll()

    suspend fun getBookByNum(num: Int): Book? = bookDao.getBookByNum(num)

    suspend fun insertBook(book: Book) {
        try {
            bookDao.insert(book)
            Log.d("LocalDataSource", "Libro insertado: ${book.title}")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error insertando libro", e)
        }
    }

    suspend fun deleteBook(book: Book) {
        try {
            bookDao.delete(book)
            Log.d("LocalDataSource", "Libro eliminado: ${book.title}")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error eliminando libro", e)
        }
    }

    suspend fun updateFavoriteStatus(num: Int, isFavorite: Boolean) {
        try {
            bookDao.updateFavoriteStatus(num, isFavorite)
            Log.d("LocalDataSource", "Libro actualizado como favorito: $isFavorite")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error actualizando favorito del libro", e)
        }
    }

    //Personajes
    fun getAllCharacters(): Flow<List<Character>> = characterDao.getAll()

    suspend fun getCharacterByName(nickname: String): Character? = characterDao.getCharacterByNickname(nickname)

    suspend fun insertCharacter(character: Character) {
        try {
            characterDao.insert(character)
            Log.d("LocalDataSource", "Personaje insertado: ${character.nickname}")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error insertando personaje", e)
        }
    }

    suspend fun deleteCharacter(character: Character) {
        try {
            characterDao.delete(character)
            Log.d("LocalDataSource", "Personaje eliminado: ${character.nickname}")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error eliminando personaje", e)
        }
    }

    suspend fun updateFavoriteStatus(nickname: String, isFavorite: Boolean) {
        try {
            characterDao.updateFavoriteStatus(nickname, isFavorite)
            Log.d("LocalDataSource", "Personaje actualizado como favorito: $isFavorite")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error actualizando favorito del personaje", e)
        }
    }

    //Casas
    fun getAllHouses(): Flow<List<House>> = houseDao.getAll()

    suspend fun getHouseByName(house: String): House? = houseDao.getHouseByHouse(house)

    suspend fun insertHouse(house: House) {
        try {
            houseDao.insert(house)
            Log.d("LocalDataSource", "Casa insertada: ${house.house}")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error insertando casa", e)
        }
    }

    suspend fun deleteHouse(house: House) {
        try {
            houseDao.delete(house)
            Log.d("LocalDataSource", "Casa eliminada: ${house.house}")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error eliminando casa", e)
        }
    }

    suspend fun updateFavoriteStatus2(house: String, isFavorite: Boolean) {
        try {
            houseDao.updateFavoriteStatus2(house, isFavorite)
            Log.d("LocalDataSource", "Casa actualizada como favorito: $isFavorite")
        } catch (e: Exception) {
            Log.e("LocalDataSource", "Error actualizando favorito de casa", e)
        }
    }
}