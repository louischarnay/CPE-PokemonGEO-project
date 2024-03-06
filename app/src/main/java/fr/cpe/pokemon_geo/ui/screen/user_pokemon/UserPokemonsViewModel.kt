package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.utils.loadPokemonFromId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserPokemonsViewModel @Inject constructor(
    private val application: Application,
    private val repository: PokemonGeoRepository
): ViewModel(){

    private val _userPokemons = MutableStateFlow<List<Pokemon>>(mutableListOf())
    val userPokemons: StateFlow<List<Pokemon>> = _userPokemons

    init {
        fetchUserPokemons()
    }

    private fun fetchUserPokemons() {
        viewModelScope.launch {
            val pokemonList = mutableListOf<Pokemon>()
            val userPokemons = repository.getAllUserPokemon()

            userPokemons.forEach {
                pokemonList.add(loadPokemonFromId(application.resources.openRawResource(R.raw.pokemons), it.pokemonId))
            }

            withContext(Dispatchers.Main) {
                _userPokemons.value = pokemonList
            }
        }
    }
}