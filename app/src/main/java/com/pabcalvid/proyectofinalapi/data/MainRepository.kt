package com.pabcalvid.proyectofinalapi.data

import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.data.local.Character
import com.pabcalvid.proyectofinalapi.data.local.LocalDataSource
import com.pabcalvid.proyectofinalapi.data.remote.RemoteDataSource
import com.pabcalvid.proyectofinalapi.data.remote.toLocalEntity
import kotlinx.coroutines.flow.Flow

class MainRepository(private val localds: LocalDataSource, private val remoteds: RemoteDataSource) {

    //Obtener todos los libros online
    suspend fun getBooks(): List<Book> {
        return try {
            val bookDTOList = remoteds.getBooks()
            bookDTOList.map { it.toLocalEntity() }
        } catch (e: Exception) {
            emptyList() // Devuelve lista vacía en caso de error
        }
    }

    //Obtener un libro aleatorio online
    suspend fun getRandomBook(): Book {
        return remoteds.getRandomBook().toLocalEntity()
    }

    //Obtener todos los libros de la base de datos local
    suspend fun getLocalBooks(): Flow<List<Book>> {
        return localds.getAll()
    }

    //Insertar un libro en la base de datos local
    fun insertLocalBook(book: Book) {
        localds.insert(book)
    }

    //Eliminar un libro de la base de datos local
    fun deleteLocalBook(book: Book) {
        localds.delete(book)
    }

    //Obtener todos los personajes online
    suspend fun getCharacters(): List<Character> {
        return try {
            val characterDTOList = remoteds.getCharacters()
            characterDTOList.map { it.toLocalEntity() }
        } catch (e: Exception) {
            emptyList() // Devuelve lista vacía en caso de error
        }
    }

    //Obtener un personaje aleatorio online
    suspend fun getRandomCharacter(): Character {
        return remoteds.getRandomCharacter().toLocalEntity()
    }

    //Obtener todos los personajes de la base de datos local
    suspend fun getLocalCharacters(): Flow<List<Character>> {
        return localds.getAllCharacters()
    }

    //Insertar un personaje en la base de datos local
    suspend fun insertLocalCharacter(character: Character) {
        localds.insertCharacter(character)
    }

    //Eliminar un personaje de la base de datos local
    suspend fun deleteLocalCharacter(character: Character) {
        localds.deleteCharacter(character)
    }
}
