package com.pabcalvid.proyectofinalapi.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("en/books")
    suspend fun getBooks(): List<BookDTO>

    @GET("en/books/random")
    suspend fun getRandomBook(): BookDTO

}