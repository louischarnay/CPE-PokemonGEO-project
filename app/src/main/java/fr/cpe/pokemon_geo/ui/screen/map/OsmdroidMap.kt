package fr.cpe.pokemon_geo.ui.screen.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import org.osmdroid.api.IMapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


@Composable
fun OsmdroidMap(osmdroidMapViewModel: OsmdroidMapViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()

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

            osmdroidMapViewModel.fetchCurrentLocationPeriodically(mapView)

            mapView
        }
    )
}