package com.pabcalvid.proyectofinalapi.data.remote

import com.google.gson.annotations.SerializedName
import com.pabcalvid.proyectofinalapi.data.local.Book

data class BookDTO(
    @SerializedName("number") val num: Int,
    @SerializedName("title") val title: String,
    @SerializedName("originalTitle") val originalTitle: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("description") val description: String,
    @SerializedName("pages") val pages: Int,
    @SerializedName("cover") val cover: String,
    @SerializedName("index") val index: Int
)

fun BookDTO.toLocalEntity() =
    Book(
        id = null,
        num = num,
        title = title,
        originalTitle = originalTitle,
        releaseDate = releaseDate,
        description = description,
        pages = pages,
        cover = cover,
        index = index
    )