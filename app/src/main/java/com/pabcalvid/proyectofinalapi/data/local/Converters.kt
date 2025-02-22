package com.pabcalvid.proyectofinalapi.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromListString(value: List<String>?): String {
        return value?.let { gson.toJson(it) } ?: "[]"
    }

    @TypeConverter
    fun toListString(value: String?): List<String> {
        return value?.let {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(it, listType)
        } ?: emptyList()
    }
}