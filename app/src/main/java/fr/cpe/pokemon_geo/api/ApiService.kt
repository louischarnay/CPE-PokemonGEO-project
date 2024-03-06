package fr.cpe.pokemon_geo.api

import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("interpreter?data=[out:json];node(around:1000,{latitude},{longitude})[amenity=pharmacy];out;")
    suspend fun getPokeCenterAround(@Path("latitude") latitude: Double, @Path("longitude") longitude: Double): Call<InterestPoint>
}