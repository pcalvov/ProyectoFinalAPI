package com.pabcalvid.proyectofinalapi.data.remote

import com.google.gson.annotations.SerializedName
import com.pabcalvid.proyectofinalapi.data.local.Character

data class CharacterDTO(
    @SerializedName("fullName") val fullName: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("hogwartsHouse") val hogwartsHouse: String,
    @SerializedName("interpretedBy") val interpretedBy: String,
    @SerializedName("children") val children: List<String>,
    @SerializedName("image") val image: String,
    @SerializedName("birthdate") val birthdate: String,
    @SerializedName("index") val index: Int
)

fun CharacterDTO.toLocalEntity() =
    Character(
        id = null,
        fullName = fullName,
        nickname = nickname,
        hogwartsHouse = hogwartsHouse,
        interpretedBy = interpretedBy,
        children = children,
        image = image,
        birthdate = birthdate,
        index = index
    )
