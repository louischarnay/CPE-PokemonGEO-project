package fr.cpe.pokemon_geo.ui.screen.map

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.database.pokestop_empty.PokestopEmptyEntity
import fr.cpe.pokemon_geo.model.interest_point.InterestPoint
import fr.cpe.pokemon_geo.model.inventory_item.INVENTORY_ITEM
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.ui.navigation.Screen
import fr.cpe.pokemon_geo.usecase.GeneratePokemonsUseCase
import fr.cpe.pokemon_geo.usecase.GetInterestPointUseCase
import fr.cpe.pokemon_geo.usecase.GetLocationUseCase
import fr.cpe.pokemon_geo.utils.findPokemonByOrder
import fr.cpe.pokemon_geo.utils.hasSameContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class OsmdroidMapViewModel @Inject constructor(
    private val application: Application,
    private val repository: PokemonGeoRepository,
    private val getLocationUseCase: GetLocationUseCase,
    private val getInterestPointUseCase: GetInterestPointUseCase,
    private val generatePokemonsUseCase: GeneratePokemonsUseCase
) : ViewModel() {

    private var mapView: MapView? = null

    private var userMarker: Marker? = null
    private var interestPointMarkers: Map<InterestPoint, Marker> = emptyMap()
    private var generatedPokemonMarkers: Map<GeneratedPokemonEntity, Marker> = emptyMap()

    fun setMapView(mapView: MapView) {
        this.mapView = mapView
    }

    fun fetchMapDataPeriodically(pokemons: List<Pokemon>, navController: NavController) {
        collectCurrentLocation()
        collectInterestPoints()
        collectGeneratedPokemons(navController, pokemons)
    }

    private fun collectCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase.invoke().collect { location ->
                if (mapView == null || location == null) return@collect

                withContext(Dispatchers.Main) {
                    val newMarker = Marker(mapView)
                    newMarker.icon = application.getDrawable(R.drawable.player)
                    newMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    newMarker.position = location
                    mapView?.overlays?.add(newMarker)

                    if (userMarker != null) {
                        mapView?.overlays?.remove(userMarker)
                    }
                    userMarker = newMarker
                    mapView?.invalidate()

                    mapView?.controller?.animateTo(location)
                }
            }
        }
    }

    private fun collectInterestPoints() {
        viewModelScope.launch(Dispatchers.IO) {
            getInterestPointUseCase.invoke().collect { interestPoints ->

                // Get only new interest points that are not already displayed
                val newInterestPoints = interestPoints.filter { interestPoint ->
                    interestPointMarkers.keys.none { it.getLatitude() == interestPoint.getLatitude() && it.getLongitude() == interestPoint.getLongitude()}
                }

                // Get only interest points that are not in the new list
                val oldInterestPoints = interestPointMarkers.keys.filter { interestPoint ->
                    interestPoints.none { it.getLatitude() == interestPoint.getLatitude() && it.getLongitude() == interestPoint.getLongitude()}
                }

                // Delete markers from oldInterestPoints
                for (interestPoint in oldInterestPoints) {
                    val marker = interestPointMarkers[interestPoint]
                    if (marker != null) {
                        mapView?.overlays?.remove(marker)
                    }
                }

                // Delete all lines from pokestopEmpty table saved more than 5min
                val currentTime = System.currentTimeMillis()
                val pokestopEmptyToDelete = repository.getAllPokestopEmpty().filter { currentTime - it.created_at > 300000 }

                pokestopEmptyToDelete.forEach { pokestop ->
                    // get marker if exists in interestPointMarkers
                    val marker = interestPointMarkers.values.find { it.position.latitude == pokestop.latitude && it.position.longitude == pokestop.longitude }

                    // when marker in interestPointMarkers, change icon to pokestop
                    marker?.icon = application.getDrawable(R.drawable.pokestop)

                    // delete line in pokestopEmpty table
                    repository.removePokestopEmptyById(pokestop.id)

                    mapView?.invalidate()
                }

                // Add markers from newInterestPoints

                try {
                    for (interestPoint in newInterestPoints) {
                            val marker = createInterestPointMarker(interestPoint)

                            if (interestPoint.isPokeCenter()) {
                                listenOnPokeCenterClick(marker)
                            } else {
                                listenOnPokestopClick(marker, interestPoint)
                            }
                        mapView?.invalidate()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun collectGeneratedPokemons(
        navController: NavController,
        pokemons: List<Pokemon>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            generatePokemonsUseCase.invoke(pokemons).collect { generatedPokemons ->
                if (mapView == null) return@collect

                val hasSamePokemons = generatedPokemonMarkers.keys.hasSameContent(generatedPokemons)
                if (hasSamePokemons) return@collect

                withContext(Dispatchers.Main) {
                    generatedPokemonMarkers.forEach { (_, marker) ->
                        mapView?.overlays?.remove(marker)
                    }

                    generatedPokemonMarkers = generatedPokemons.associateWith { generatedPokemon ->
                        val marker = Marker(mapView)
                        marker.position = GeoPoint(generatedPokemon.latitude, generatedPokemon.longitude)
                        val pokemonData = findPokemonByOrder(pokemons, generatedPokemon.pokemonOrder)
                        marker.title = pokemonData.getName()
                        marker.icon = application.getDrawable(pokemonData.getFrontResource())
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        mapView?.overlays?.add(marker)

                        marker.setOnMarkerClickListener { _, _ ->
                            if (generatedPokemon.id != null) {
                                navController.navigate(Screen.PokemonFighterChoice.withArgs(generatedPokemon.id))
                            }
                            true // Return true to consume the event
                        }

                        marker
                    }

                    mapView?.invalidate()
                }
            }
        }
    }

    private suspend fun createInterestPointMarker(interestPoint: InterestPoint): Marker {
        if (mapView == null) throw IllegalStateException("Map view is not set")

        val marker = Marker(mapView)
        marker.position = GeoPoint(interestPoint.getLatitude(), interestPoint.getLongitude())
        marker.title = interestPoint.getName()
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

        if (interestPoint.isPokeCenter()) {
            marker.icon = application.getDrawable(R.drawable.pokecenter)
        } else {
            val pokestopEmpty = repository.getPokestopEmptyById(interestPoint.getName())
            marker.icon = if (pokestopEmpty != null)
                application.getDrawable(R.drawable.pokestop_empty)
            else
                application.getDrawable(R.drawable.pokestop)
        }

        mapView?.overlays?.add(marker)
        interestPointMarkers = interestPointMarkers + (interestPoint to marker)

        return marker
    }

    private suspend fun listenOnPokeCenterClick(marker: Marker) {
        marker.setOnMarkerClickListener { _, _ ->
            // Launch a coroutine within the existing coroutine scope
            viewModelScope.launch {
                // Call the suspending function within the coroutine
                repository.healAllUserPokemons()
                Toast.makeText(
                    application,
                    "Tout vos pokemons ont été soignés",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true // Return true to consume the event
        }
    }

    private suspend fun listenOnPokestopClick(marker: Marker, interestPoint: InterestPoint) {
        marker.setOnMarkerClickListener { _, _ ->
            // Launch a coroutine within the existing coroutine scope
            viewModelScope.launch {
                // Call the suspending function within the coroutine
                if (repository.getPokestopEmptyById(interestPoint.getName()) != null) {
                    Toast.makeText(
                        application,
                        "Vous avez déjà récupéré les objets de ce pokestop",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }

                val randomItem = if ((0..9).random() < 9) INVENTORY_ITEM.pokeball else INVENTORY_ITEM.masterball
                val randomQuantity = (1..5).random()

                repository.appendUserInventoryQuantity(randomItem.name, randomQuantity)
                Toast.makeText(
                    application,
                    "Vous avez reçu $randomQuantity ${randomItem.name}",
                    Toast.LENGTH_SHORT
                ).show()

                marker.icon = application.getDrawable(R.drawable.pokestop_empty)
                repository.insertPokestopEmpty(
                    PokestopEmptyEntity(
                        interestPoint.getName(),
                        interestPoint.getLatitude(),
                        interestPoint.getLongitude(),
                        System.currentTimeMillis()
                    )
                )
            }
            true // Return true to consume the event
        }
    }
}
