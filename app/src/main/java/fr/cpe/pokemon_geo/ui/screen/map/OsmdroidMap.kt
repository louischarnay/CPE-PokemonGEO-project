package fr.cpe.pokemon_geo.ui.screen.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


@SuppressLint("ClickableViewAccessibility")
@Composable
fun OsmdroidMap(
    navController: NavController,
    pokemons: List<Pokemon>,
    osmdroidMapViewModel: OsmdroidMapViewModel = hiltViewModel()
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            // SETUP MAP
            val mapView = MapView(context)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setTilesScaledToDpi(true)

            // LOCK ALL MOVEMENTS
            mapView.setMultiTouchControls(false)
            //disable movement on map
            //mapView.setOnTouchListener { view, motionEvent -> true  }
            mapView.setOnGenericMotionListener { view, motionEvent -> true  }
            mapView.setOnKeyListener { view, i, keyEvent -> true  }

            // SET ZOOM LIMITS
            val zoomLevel = 18.0
            mapView.minZoomLevel = zoomLevel
            mapView.maxZoomLevel = zoomLevel
            mapView.controller.setZoom(zoomLevel)

            osmdroidMapViewModel.setMapView(mapView)

            mapView
        }
    )
}