package fr.cpe.pokemon_geo.usecase

import android.util.Log
import fr.cpe.pokemon_geo.api.ApiClient
import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import fr.cpe.pokemon_geo.model.interest_point.InterestPointResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class GetInterestPointUseCase @Inject constructor(
    private val getlocationUseCase: GetLocationUseCase
){
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var lastLatitude = 0.0

    private var lastLongitude = 0.0

    private var lastGeoPoint = GeoPoint(lastLatitude, lastLongitude)

    operator fun invoke(): Flow<InterestPoint>? {
        return run()
    }
    private fun run(): Flow<InterestPoint>? {
        coroutineScope.launch(){
            while (true) {
                Timber.d("GetInterestPointUseCase.run")
                getlocationUseCase.invoke().collect { location ->
                    if (location != null) {
                        var latitude = location.latitude
                        var longitude = location.longitude

                        //if (location.distanceToAsDouble(lastGeoPoint) > 100.0 || (lastLatitude == 0.0 && lastLongitude == 0.0)) {
                        lastLatitude = latitude
                        lastLongitude = longitude
                        lastGeoPoint = GeoPoint(lastLatitude, lastLongitude)
                        getInterestPoint()
                        //}
                    }
                }
            }
        }
        return null
    }

    private suspend fun getInterestPoint(): List<InterestPoint> {
        //Call the API to get the interest point
        val url = "[out:json];\n" + "\n" + "node(around:1000,${lastLatitude},${lastLongitude})[amenity=pharmacy];\n" + "\n" + "out;"
        val response = ApiClient.apiService.getPokeCenterAround(url)
        Log.d("InterestPoint", "Response: $response")

        if (response.isSuccessful) {
            Log.d("InterestPoint", "Response: ${response.body()}")
            val elements = response.body()?.elements
            val interestPoints = mutableListOf<InterestPoint>()
            if (elements != null) {
                for (element in elements) {
                    val interestPoint = InterestPoint(
                        element.tags.name,
                        element.lat,
                        element.lon
                    )
                    Log.d("InterestPoint", "InterestPoint: $interestPoint")
                    interestPoints.add(interestPoint)
                }
            }
            return interestPoints
        } else {
            Log.e("InterestPoint", "Error: ${response.message()}")
        }
        return emptyList()
    }
}