package com.example.minhapokedex.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PokeApi {

    @GET("v2/cards/")

    fun getCards(
        @Header("X-Api-Key") apiKey: String,
        @Query("q") query: String
    ): Call<Root>
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.pokemontcg.io/"

    val api: PokeApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)
    }

}

