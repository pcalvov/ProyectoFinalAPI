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

}