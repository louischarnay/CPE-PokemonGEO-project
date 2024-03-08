package fr.cpe.pokemon_geo.ui.screen.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import org.osmdroid.api.IMapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


@SuppressLint("ClickableViewAccessibility")
@Composable
fun OsmdroidMap(osmdroidMapViewModel: OsmdroidMapViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            // SETUP MAP
            val mapView = MapView(context)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setTilesScaledToDpi(true)

            // LOCK ALL MOVEMENTS
            mapView.setMultiTouchControls(false)
            mapView.setOnTouchListener { view, motionEvent -> true }
            mapView.setOnGenericMotionListener { view, motionEvent -> true  }
            mapView.setOnKeyListener { view, i, keyEvent -> true  }

            // SET ZOOM LIMITS
            val zoomLevel = 18.0
            mapView.minZoomLevel = zoomLevel
            mapView.maxZoomLevel = zoomLevel

            // SET MAP CENTER AND ZOOM
            val mapController: IMapController = mapView.controller
            mapController.setZoom(zoomLevel)
            val center = osmdroidMapViewModel.currentLocation.value ?: osmdroidMapViewModel.lyon
            mapController.setCenter(center)

            osmdroidMapViewModel.fetchCurrentLocationPeriodically(mapView)

            mapView
        }
    )
}