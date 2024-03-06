package fr.cpe.pokemon_geo.usecase

import android.util.Log
import fr.cpe.pokemon_geo.api.ApiClient
import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    fun run(): Flow<InterestPoint>? {
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

    private suspend fun getInterestPoint() {
        //Call the API to get the interest point
        val call = ApiClient.apiService.getPokeCenterAround(lastLatitude, lastLongitude)
        call.enqueue(object : Callback<InterestPoint> {
            override fun onResponse(call: Call<InterestPoint>, response: Response<InterestPoint>) {
                if (response.isSuccessful) {
                    val interestPoint = response.body()
                    Log.d("InterestPoint", interestPoint.toString())
                }
            }
            override fun onFailure(call: Call<InterestPoint>, t: Throwable) {
                println("Error")
            }
        })
    }
}