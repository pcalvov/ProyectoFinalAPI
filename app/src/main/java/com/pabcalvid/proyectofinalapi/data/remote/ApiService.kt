package com.pabcalvid.proyectofinalapi.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("info.0.json")
    suspend fun getLastBook(): BookDTO

    @GET("{num}/info.0.json")
    suspend fun getBook(@Path("num") num: Int): BookDTO

}