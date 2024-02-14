package fr.cpe.pokemon_geo.ui.screen.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.api.IMapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


@Composable
fun OsmdroidMapView() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val mapView = MapView(context)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setMultiTouchControls(true)

            val mapController: IMapController = mapView.controller
            mapController.setZoom(8.0)

            val startPoint = GeoPoint(45.75, 4.85)
            mapController.setCenter(startPoint)
            mapController.animateTo(startPoint, 16.0, 2000)

            mapView
        }
    )
}