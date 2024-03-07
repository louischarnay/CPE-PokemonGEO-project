package fr.cpe.pokemon_geo.usecase

import android.util.Log
import fr.cpe.pokemon_geo.api.ApiClient
import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import fr.cpe.pokemon_geo.model.interest_point.InterestPointResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class GetInterestPointUseCase @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
){
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var lastLatitude = 0.0
    private var lastLongitude = 0.0

    operator fun invoke(): Flow<List<InterestPoint>> = callbackFlow {
        coroutineScope.launch {
            while (true) {
                getLocationUseCase.invoke().collect { location ->
                    if (location == null) return@collect

                    val lastGeoPoint = GeoPoint(lastLatitude, lastLongitude)

                    if (location.distanceToAsDouble(lastGeoPoint) > 100.0) {
                        lastLatitude = location.latitude
                        lastLongitude = location.longitude

                        trySend(getInterestPoint())
                    }
                }
            }
        }

        awaitClose {
            coroutineScope.coroutineContext.cancel()
        }
    }

    private suspend fun getInterestPoint(): List<InterestPoint> {
        //Call the API to get the interest point
        val url = "[out:json];\n" + "\n" + "node(around:1000,${lastLatitude},${lastLongitude})[amenity=pharmacy];\n" + "\n" + "out;"
        val response = ApiClient.apiService.getPokeCenterAround(url)

        if (response.isSuccessful) {
            val elements = response.body()?.elements
            val interestPoints = mutableListOf<InterestPoint>()
            if (elements != null) {
                for (element in elements) {
                    val interestPoint = InterestPoint(
                        element.tags.name,
                        element.lat,
                        element.lon
                    )
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