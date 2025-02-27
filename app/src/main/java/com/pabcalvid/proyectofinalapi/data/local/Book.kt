package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "books", indices = [Index(value = ["index"], unique = true)])
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val num: Int,
    val title: String,
    val originalTitle: String,
    val releaseDate: String,
    val description: String,
    val pages: Int,
    val cover: String,
    val index: Int,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
)
