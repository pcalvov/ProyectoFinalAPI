package com.pabcalvid.proyectofinalapi.data

import com.pabcalvid.proyectofinalapi.data.local.*
import com.pabcalvid.proyectofinalapi.data.remote.*
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val localds: LocalDataSource,
    private val remoteds: RemoteDataSource
) {

    //Libros

    suspend fun getBooks(): List<Book> {
        return try {
            remoteds.getBooks().map { it.toLocalEntity() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getAllBooks(): Flow<List<Book>> {
        return localds.getAllBooks()
    }

    suspend fun getRandomBook(): Book {
        return remoteds.getRandomBook().toLocalEntity()
    }

    suspend fun toggleFavoriteBook(book: Book) {
        val localBook = localds.getBookByNum(book.num)

        if (localBook != null) {
            if (localBook.isFavorite) {
                localds.deleteBook(localBook)
            } else {
                localds.updateFavoriteStatus(localBook.num, true)
            }
        } else {
            localds.insertBook(book.copy(isFavorite = true))
        }
    }

    //Personajes

    suspend fun getCharacters(): List<Character> {
        return try {
            remoteds.getCharacters().map { it.toLocalEntity() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getRandomCharacter(): Character {
        return remoteds.getRandomCharacter().toLocalEntity()
    }

    fun getAllCharacters(): Flow<List<Character>> {
        return localds.getAllCharacters()
    }

    suspend fun toggleFavoriteCharacter(character: Character){
        val localCharacter = localds.getCharacterByName(character.nickname)
        if (localCharacter != null) {
            if (localCharacter.isFavorite) {
                localds.deleteCharacter(localCharacter)
            } else {
                localds.updateFavoriteStatus(localCharacter.nickname, true)
            }
        } else {
            localds.insertCharacter(character.copy(isFavorite = true))
        }
    }

    //Casas

    suspend fun getHouses(): List<House> {
        return try {
            remoteds.getHouses().map { it.toLocalEntity() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getRandomHouse(): House {
        return remoteds.getRandomHouse().toLocalEntity()
    }

    fun getAllHouses(): Flow<List<House>> {
        return localds.getAllHouses()
    }

    suspend fun toggleFavoriteHouse(house: House){
        val localHouse = localds.getHouseByName(house.house)
        if (localHouse != null) {
            if (localHouse.isFavorite) {
                localds.deleteHouse(localHouse)
            } else {
                localds.updateFavoriteStatus2(localHouse.house, true)
            }
        } else {
            localds.insertHouse(house.copy(isFavorite = true))
        }
    }
}