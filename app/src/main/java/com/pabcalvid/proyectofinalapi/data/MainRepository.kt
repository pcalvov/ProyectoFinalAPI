package com.pabcalvid.proyectofinalapi.data

import com.pabcalvid.proyectofinalapi.data.local.*
import com.pabcalvid.proyectofinalapi.data.remote.*
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val localds: LocalDataSource,
    private val remoteds: RemoteDataSource
) {

    // 📚 Libros 📚

    suspend fun getBooks(): List<Book> {
        return try {
            remoteds.getBooks().map { it.toLocalEntity() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getRandomBook(): Book {
        return remoteds.getRandomBook().toLocalEntity()
    }

    suspend fun getLocalBooks(): Flow<List<Book>> {
        return localds.getAllBooks()
    }

    suspend fun insertLocalBook(book: Book) {
        localds.insertBook(book)
    }

    suspend fun deleteLocalBook(book: Book) {
        localds.deleteBook(book)
    }

    // 🧙‍♂️ Personajes 🧙‍♂️

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

    suspend fun getLocalCharacters(): Flow<List<Character>> {
        return localds.getAllCharacters()
    }

    suspend fun insertLocalCharacter(character: Character) {
        localds.insertCharacter(character)
    }

    suspend fun deleteLocalCharacter(character: Character) {
        localds.deleteCharacter(character)
    }

    // 🏰 Casas 🏰

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

    suspend fun getLocalHouses(): Flow<List<House>> {
        return localds.getAllHouses()
    }

    suspend fun insertLocalHouse(house: House) {
        localds.insertHouse(house)
    }

    suspend fun deleteLocalHouse(house: House) {
        localds.deleteHouse(house)
    }
}