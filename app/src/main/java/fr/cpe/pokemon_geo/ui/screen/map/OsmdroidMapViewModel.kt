package fr.cpe.pokemon_geo.ui.screen.map

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import fr.cpe.pokemon_geo.usecase.GetInterestPointUseCase
import fr.cpe.pokemon_geo.usecase.GetLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import javax.inject.Inject

@HiltViewModel
class OsmdroidMapViewModel @Inject constructor(
    private val application: Application,
    private val getLocationUseCase: GetLocationUseCase,
    private val getInterestPointUseCase: GetInterestPointUseCase
) : ViewModel() {

    val lyon = GeoPoint(45.7578137, 4.8320114)

    private val _currentLocation: MutableState<GeoPoint?> = mutableStateOf(null)
    val currentLocation: State<GeoPoint?> = _currentLocation

    private var userMarker: Marker? = null
    private var interestPointMarkers: Map<InterestPoint, Marker> = emptyMap()

    fun fetchCurrentLocationPeriodically(mapView: MapView) {
        collectCurrentLocation(mapView)
        collectInterestPoints(mapView)
    }

    private fun collectCurrentLocation(mapView: MapView) {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase.invoke().collect { location ->
                _currentLocation.value = location

                if (location == null) return@collect

                withContext(Dispatchers.Main) {
                    if (userMarker == null) {
                        userMarker = Marker(mapView)
                        userMarker?.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        mapView.overlays.add(userMarker)
                    }
                    userMarker?.position = location
                    mapView.invalidate()

                    mapView.controller.animateTo(location)
                }
            }
        }
    }

    private fun collectInterestPoints(mapView: MapView) {
        viewModelScope.launch(Dispatchers.IO) {
            getInterestPointUseCase.invoke().collect { interestPoints ->
                if (interestPoints.isEmpty()) return@collect

                withContext(Dispatchers.Main) {
                    interestPointMarkers.forEach { (_, marker) ->
                        mapView.overlays.remove(marker)
                    }
                    interestPointMarkers = interestPoints.associateWith { interestPoint ->
                        val marker = Marker(mapView)
                        marker.position = GeoPoint(interestPoint.getLatitude(), interestPoint.getLongitude())
                        marker.title = interestPoint.getName()
                        if (interestPoint.isPokeCenter()){
                            marker.icon = application.getDrawable(R.drawable.pokecenter)
                        } else {
                            marker.icon = application.getDrawable(R.drawable.pokestop)
                        }
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        mapView.overlays.add(marker)
                        marker
                    }
                    mapView.invalidate()
                }
            }
        }
    }
}
