package com.pabcalvid.proyectofinalapi.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_BASE_URL = "https://potterapi-fedeperin.vercel.app"

object RetrofitBuilder {
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(getOkHttpClientLoggedBuilder())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /* Función que devuelve el OkHttpClient  sin interceptor, para emplear al pasar a producción */
    private fun getOkHttpClientBuilder(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    /* Función que devuelve el OkHttpClient  cin interceptor, para hacer debug  */
    private fun getOkHttpClientLoggedBuilder(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}