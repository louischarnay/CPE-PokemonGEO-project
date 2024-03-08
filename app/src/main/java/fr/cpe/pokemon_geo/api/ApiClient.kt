package fr.cpe.pokemon_geo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object InterestPointApiClient {
    private const val BASE_URL = "https://overpass-api.de/api/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
object ApiClient {
    val apiService: ApiService by lazy {
        InterestPointApiClient.retrofit.create(ApiService::class.java)
    }
}