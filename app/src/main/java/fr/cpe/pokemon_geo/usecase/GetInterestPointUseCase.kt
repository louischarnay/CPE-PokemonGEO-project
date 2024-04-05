package fr.cpe.pokemon_geo.usecase

import fr.cpe.pokemon_geo.api.ApiClient
import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import timber.log.Timber
import javax.inject.Inject

class GetInterestPointUseCase @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
){
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var lastLatitude = 0.0
    private var lastLongitude = 0.0

    operator fun invoke(): Flow<List<InterestPoint>> = callbackFlow {
        coroutineScope.launch {
            getLocationUseCase.invoke().collect { location ->
                if (location == null) return@collect

                val lastGeoPoint = GeoPoint(lastLatitude, lastLongitude)

                if (location.distanceToAsDouble(lastGeoPoint) > 50.0) {
                    lastLatitude = location.latitude
                    lastLongitude = location.longitude

                    trySend(getInterestPoint())
                }
            }
        }

        awaitClose {
            coroutineScope.coroutineContext.cancel()
        }
    }

    private suspend fun getInterestPoint(): List<InterestPoint> {
        val interestPoints = mutableListOf<InterestPoint>()

        interestPoints.addAll(getPokeCenterAround())
        interestPoints.addAll(getPokeStopAround())

        return interestPoints
    }

    private suspend fun getPokeCenterAround(): List<InterestPoint> {
        val url = "[out:json];\n" + "\n" + "node(around:1000,${lastLatitude},${lastLongitude})[amenity=pharmacy];\n" + "\n" + "out;"
        val response = ApiClient.apiService.getInterestPoint(url)

        if (response.isSuccessful) {
            val elements = response.body()?.elements
            val interestPoints = mutableListOf<InterestPoint>()
            if (elements != null) {
                for (element in elements) {
                    val interestPoint = InterestPoint(
                        element.tags.name,
                        element.lat,
                        element.lon,
                        true
                    )
                    interestPoints.add(interestPoint)
                }
            }
            return interestPoints
        } else {
            Timber.e("Error: ${response.message()}")
        }
        return emptyList()
    }

    private suspend fun getPokeStopAround(): List<InterestPoint> {
        val url = "[out:json];\n" + "\n" + "node(around:1000,${lastLatitude},${lastLongitude})[shop];\n" + "\n" + "out;"
        val response = ApiClient.apiService.getInterestPoint(url)

        if (response.isSuccessful) {
            val elements = response.body()?.elements
            val interestPoints = mutableListOf<InterestPoint>()
            if (elements != null) {
                for (element in elements) {
                    val interestPoint = InterestPoint(
                        element.tags.name,
                        element.lat,
                        element.lon,
                        false
                    )
                    interestPoints.add(interestPoint)
                }
            }
            return interestPoints
        } else {
            Timber.e("Error: ${response.message()}")
        }
        return emptyList()
    }
}