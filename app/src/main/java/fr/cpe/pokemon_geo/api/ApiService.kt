package fr.cpe.pokemon_geo.api

import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("interpreter?data=%5Bout%3Ajson%5D%3B%0A%0Anode%28around%3A100%2C45.784225%2C4.868871%29%5Bamenity%3Dpharmacy%5D%3B%0A%0Aout%3B&target=mapql")
    suspend fun getPokeCenterAround(): Call<InterestPoint>
}