package com.pabcalvid.proyectofinalapi.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/en/books")
    suspend fun getBooks(): List<BookDTO>

    @GET("/en/books/random")
    suspend fun getRandomBook(): BookDTO

    @GET("/en/characters")
    suspend fun getCharacters(): List<CharacterDTO>

    @GET("/en/characters/random")
    suspend fun getRandomCharacter(): CharacterDTO

    @GET("/en/houses")
    suspend fun getHouses(): List<HouseDTO>

    @GET("/en/houses/random")
    suspend fun getRandomHouse(): HouseDTO
}