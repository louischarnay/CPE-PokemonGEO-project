package fr.cpe.pokemon_geo.ui.screen.map

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.usecase.GetLocationUseCase
import fr.cpe.pokemon_geo.utils.ONE_SECOND_IN_MILLIS
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

@HiltViewModel
class OsmdroidMapViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val repository: PokemonGeoRepository
) : ViewModel() {

    val lyon = GeoPoint(45.7578137, 4.8320114)

    private val _currentLocation: MutableState<GeoPoint?> = mutableStateOf(null)
    val currentLocation: State<GeoPoint?> = _currentLocation

    private val _generatedPokemons: MutableState<List<GeneratedPokemonEntity>> = mutableStateOf(mutableListOf())
    val generatedPokemons: State<List<GeneratedPokemonEntity>> = _generatedPokemons

    init {
        fetchMapDataPeriodically()
    }

    private fun fetchMapDataPeriodically() {
        viewModelScope.launch {
            while (true) {
                fetchCurrentLocation()
                fetchGeneratedPokemons()
                delay(5 * ONE_SECOND_IN_MILLIS)
            }
        }
    }

    private suspend fun fetchCurrentLocation() {
        getLocationUseCase.invoke().collect { location ->
            _currentLocation.value = location
        }
    }

    private suspend fun fetchGeneratedPokemons() {
        _generatedPokemons.value = repository.getAllGeneratedPokemon()
    }
}
