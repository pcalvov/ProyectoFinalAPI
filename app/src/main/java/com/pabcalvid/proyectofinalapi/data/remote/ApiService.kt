package com.pabcalvid.proyectofinalapi.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/books")
    suspend fun getBooks(): List<BookDTO>

    @GET("/books/random")
    suspend fun getRandomBook(): BookDTO

}