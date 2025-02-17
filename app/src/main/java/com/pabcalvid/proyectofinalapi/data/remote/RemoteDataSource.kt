package com.pabcalvid.proyectofinalapi.data.remote

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getBooks() = apiService.getBooks()

    suspend fun getRandomBook() = apiService.getRandomBook()

}