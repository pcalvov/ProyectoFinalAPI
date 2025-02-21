package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "characters", indices = [Index(value = ["index"], unique = true)])

data class Character(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val fullName: String,
    val nickname: String,
    val hogwartsHouse: String,
    val interpretedBy: String,
    val children: List<String>,
    val image: String,
    val birthdate: String,
    val index: Int
)