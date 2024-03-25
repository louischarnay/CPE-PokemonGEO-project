package fr.cpe.pokemon_geo.ui.screen.map

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import fr.cpe.pokemon_geo.model.inventory_item.INVENTORY_ITEM
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.usecase.GeneratePokemonsUseCase
import fr.cpe.pokemon_geo.usecase.GetInterestPointUseCase
import fr.cpe.pokemon_geo.usecase.GetLocationUseCase
import fr.cpe.pokemon_geo.utils.findPokemonById
import fr.cpe.pokemon_geo.utils.hasSameContent
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
    private val repository: PokemonGeoRepository,
    private val getLocationUseCase: GetLocationUseCase,
    private val getInterestPointUseCase: GetInterestPointUseCase,
    private val generatePokemonsUseCase: GeneratePokemonsUseCase
) : ViewModel() {

    private var userMarker: Marker? = null
    private var interestPointMarkers: Map<InterestPoint, Marker> = emptyMap()
    private var generatedPokemonMarkers: Map<GeneratedPokemonEntity, Marker> = emptyMap()

    fun fetchMapDataPeriodically(mapView: MapView, pokemons: List<Pokemon>) {
        collectCurrentLocation(mapView)
        collectInterestPoints(mapView)
        collectGeneratedPokemons(mapView, pokemons)
    }

    private fun collectCurrentLocation(mapView: MapView) {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase.invoke().collect { location ->
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
                val hasSameInterestPoints = interestPointMarkers.keys.hasSameContent(interestPoints)
                if (hasSameInterestPoints) return@collect

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

                        marker.setOnMarkerClickListener { marker, _ ->
                            // Handle marker click event here
                            if (interestPoint.isPokeCenter()){
                                // Launch a coroutine within the existing coroutine scope
                                viewModelScope.launch {
                                    // Call the suspending function within the coroutine
                                    repository.healAllUserPokemons()
                                    Toast.makeText(application, "Tout vos pokemons ont été soignés", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                // Launch a coroutine within the existing coroutine scope
                                viewModelScope.launch {
                                    // Call the suspending function within the coroutine
                                    if (Math.random() < 0.1) {
                                        repository.appendUserInventoryQuantity(INVENTORY_ITEM.masterball.name, 1)
                                        Toast.makeText(application, "Vous avez reçu une masterball", Toast.LENGTH_SHORT).show()
                                    } else {
                                        repository.appendUserInventoryQuantity(INVENTORY_ITEM.pokeball.name, 5)
                                        Toast.makeText(application, "Vous avez reçu 5 pokeball", Toast.LENGTH_SHORT).show()
                                    }
                                    mapView.invalidate()
                                }
                                marker.icon = application.getDrawable(R.drawable.pokestop_empty)
                            }
                            true // Return true to consume the event
                        }
                        marker
                    }
                    mapView.invalidate()
                }
            }
        }
    }

    private fun collectGeneratedPokemons(mapView: MapView, pokemons: List<Pokemon>) {
        viewModelScope.launch(Dispatchers.IO) {
            generatePokemonsUseCase.invoke(pokemons).collect { generatedPokemons ->
                val hasSamePokemons = generatedPokemonMarkers.keys.hasSameContent(generatedPokemons)
                if (hasSamePokemons) return@collect

                withContext(Dispatchers.Main) {
                    generatedPokemonMarkers.forEach { (_, marker) ->
                        mapView.overlays.remove(marker)
                    }

                    generatedPokemonMarkers = generatedPokemons.associateWith { generatedPokemon ->
                        val marker = Marker(mapView)
                        marker.position = GeoPoint(generatedPokemon.latitude, generatedPokemon.longitude)
                        val pokemonData = findPokemonById(pokemons, generatedPokemon.pokemonId)
                        marker.title = pokemonData.getName()
                        marker.icon = application.getDrawable(pokemonData.getFrontResource())
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        mapView.overlays.add(marker)

                        marker.setOnMarkerClickListener { marker, _ ->
                            // Handle marker click event here
                            // For example, you can show a toast with marker title
                            Toast.makeText(application, "Marker Clicked: ${marker.title}", Toast.LENGTH_SHORT).show()
                            true // Return true to consume the event
                        }

                        marker
                    }

                    mapView.invalidate()
                }
            }
        }
    }
}
