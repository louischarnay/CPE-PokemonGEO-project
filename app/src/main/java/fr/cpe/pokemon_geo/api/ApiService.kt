package fr.cpe.pokemon_geo.api

import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import fr.cpe.pokemon_geo.model.interest_point.InterestPointResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("interpreter")
    suspend fun getPokeCenterAround(
        @Query("data") data: String
    ): Response<InterestPointResponse>
}