package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "houses", indices = [Index(value = ["index"], unique = true)])
@TypeConverters(Converters::class)
data class House(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val house: String,
    val emoji: String,
    val founder: String,
    val colors: List<String>,
    val animal: String,
    val index: Int,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean = false
)
