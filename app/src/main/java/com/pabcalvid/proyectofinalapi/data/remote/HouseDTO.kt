package com.pabcalvid.proyectofinalapi.data.remote

import com.google.gson.annotations.SerializedName
import com.pabcalvid.proyectofinalapi.data.local.House

data class HouseDTO(
    @SerializedName("house") val house: String,
    @SerializedName("emoji") val emoji: String,
    @SerializedName("founder") val founder: String,
    @SerializedName("colors") val colors: List<String>,
    @SerializedName("animal") val animal: String,
    @SerializedName("index") val index: Int
)

fun HouseDTO.toLocalEntity() =
    House(
        id = null,
        house = house,
        emoji = emoji,
        founder = founder,
        colors = colors,
        animal = animal,
        index = index
    )
