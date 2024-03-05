package fr.cpe.pokemon_geo.ui.screen.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.api.IMapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


@Composable
fun OsmdroidMap(osmdroidMapViewModel: OsmdroidMapViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()

    var isMyMarkerAdded = false

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val mapView = MapView(context)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setMultiTouchControls(true)

            // SET MAP CENTER AND ZOOM
            val mapController: IMapController = mapView.controller
            mapController.setZoom(17.0)
            val center = osmdroidMapViewModel.currentLocation.value ?: osmdroidMapViewModel.lyon
            mapController.setCenter(center)

            // ADD USER MARKER
            val marker = Marker(mapView)
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            if (osmdroidMapViewModel.currentLocation.value != null) {
                marker.position = osmdroidMapViewModel.currentLocation.value
                mapView.overlays.add(marker)
                isMyMarkerAdded = true
            }

            coroutineScope.launch {
                while (true) {
                    delay(3_000)

                    val newLocation = osmdroidMapViewModel.currentLocation.value ?: continue

                    marker.position = newLocation

                    if (!isMyMarkerAdded) {
                        mapView.overlays.add(marker)
                        isMyMarkerAdded = true
                        mapController.animateTo(newLocation)
                    }

                    mapView.invalidate() // Invalidate the map view to redraw
                }
            }

            mapView
        }
    )
}