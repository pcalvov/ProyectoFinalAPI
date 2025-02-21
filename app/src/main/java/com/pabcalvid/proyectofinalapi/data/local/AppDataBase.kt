package com.pabcalvid.proyectofinalapi.data.local

import android.content.Context
import androidx.room.*

@Database(entities = [Book::class, Character::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)  // ✅ Asegura que se usa correctamente
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, "HarryPotterdb.sql"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}