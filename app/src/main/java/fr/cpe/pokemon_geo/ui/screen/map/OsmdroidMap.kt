package fr.cpe.pokemon_geo.ui.screen.map

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.utils.ONE_SECOND_IN_MILLIS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.api.IMapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


@Composable
fun OsmdroidMap(pokemons: List<Pokemon>, osmdroidMapViewModel: OsmdroidMapViewModel = hiltViewModel()) {
    val coroutineScope = rememberCoroutineScope()

    var isMyMarkerAdded = false

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val mapView = MapView(context)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setMultiTouchControls(false)
            mapView.setOnTouchListener { view, motionEvent -> true  }

            // SET MAP CENTER AND ZOOM
            val mapController: IMapController = mapView.controller
            mapController.setZoom(18.0)
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
                    delay(ONE_SECOND_IN_MILLIS)

                    val newLocation = osmdroidMapViewModel.currentLocation.value ?: continue
                    marker.position = newLocation
                    mapController.animateTo(newLocation)

                    if (!isMyMarkerAdded) {
                        mapView.overlays.add(marker)
                        isMyMarkerAdded = true
                    }

                    osmdroidMapViewModel.generatedPokemons.value.forEach { generatedPokemon ->
                        // check if the pokemon is already on the map
                        val pokemonAlreadyOnMap = mapView.overlays.any {
                            it is Marker && it.position.latitude == generatedPokemon.latitude && it.position.longitude == generatedPokemon.longitude
                        }
                        Log.e("OsmdroidMap", "Pokemon already on map: $pokemonAlreadyOnMap")
                        if (pokemonAlreadyOnMap) return@forEach

                        Log.e("OsmdroidMap", "Adding pokemon on the map")
                        val pokemonData = pokemons.find { it.getOrder() == generatedPokemon.pokemonId }
                        if (pokemonData != null) {
                            val pokemonMarker = Marker(mapView)
                            pokemonMarker.icon = context.getDrawable(pokemonData.getFrontResource())
                            pokemonMarker.position = GeoPoint(generatedPokemon.latitude, generatedPokemon.longitude)
                            mapView.overlays.add(pokemonMarker)
                        }
                    }

                    mapView.invalidate() // Invalidate the map view to redraw
                }
            }

            mapView
        }
    )
}