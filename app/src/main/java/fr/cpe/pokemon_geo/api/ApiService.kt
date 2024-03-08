package fr.cpe.pokemon_geo.api

import fr.cpe.pokemon_geo.model.interest_point.InterestPointResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("interpreter")
    suspend fun getInterestPoint(
        @Query("data") data: String
    ): Response<InterestPointResponse>
}