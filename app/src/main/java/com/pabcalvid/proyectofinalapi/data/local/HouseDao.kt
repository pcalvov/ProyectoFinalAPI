package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseDao {

    @Query("SELECT * FROM houses")
    fun getAll(): Flow<List<House>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(house: House): Long

    @Delete
    fun delete(house: House)
}
