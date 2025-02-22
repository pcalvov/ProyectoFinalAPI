package com.pabcalvid.proyectofinalapi.data.local

import android.content.Context
import androidx.room.*

@Database(entities = [Book::class, Character::class, House::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun characterDao(): CharacterDao
    abstract fun houseDao(): HouseDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, "HarryPotterdb.sql"
                )
                    .fallbackToDestructiveMigration() // Maneja cambios en la base de datos sin errores
                    .build()
                    .also { Instance = it }
            }
        }
    }
}