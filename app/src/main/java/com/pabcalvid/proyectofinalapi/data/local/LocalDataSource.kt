package com.pabcalvid.proyectofinalapi.data.local

import android.content.Context
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn

class LocalDataSource(applicationContext: Context) {
    private val db: AppDataBase = AppDataBase.getDatabase(applicationContext)

    suspend fun getAll(): Flow<List<Book>> {
        return db.bookDao().getAll().stateIn(GlobalScope)
    }

    fun insert(book: Book) {
        val result = db.bookDao().insert(book)
        Log.d("LOCALDS", "Resultado: $result")
    }

    fun delete(book: Book) {
        db.bookDao().delete(book)
    }

    suspend fun getAllCharacters(): Flow<List<Character>> {
        return db.characterDao().getAll().stateIn(GlobalScope)
    }

    suspend fun insertCharacter(character: Character) {
        val result = db.characterDao().insert(character)
        Log.d("LOCALDS", "Resultado: $result")
    }

    suspend fun deleteCharacter(character: Character) {
        db.characterDao().delete(character)
    }
}