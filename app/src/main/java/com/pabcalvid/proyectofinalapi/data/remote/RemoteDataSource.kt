package com.pabcalvid.proyectofinalapi.data.remote



class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getLastBook() = apiService.getLastBook()

    suspend fun getBook(number: Int) = apiService.getBook(number)

}