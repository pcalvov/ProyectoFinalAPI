package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "books", indices = [Index(value = ["index"], unique = true)])

data class Book (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val num: Int,
    val title: String,
    val originalTitle: String,
    val releaseDate: String,
    val description: String,
    val pages: Int,
    val cover: String,
    val index: Int
)
