package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.utils.buildPokemonWithStatsFromOrder
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

            val json = application.resources.openRawResource(R.raw.pokemons).bufferedReader().readText()
            userPokemons.forEach {
                pokemonList.add(buildPokemonWithStatsFromOrder(json, it.pokemonOrder, it.id ?: 0, it.hpMax, it.hpLost, it.attack))
            }

            withContext(Dispatchers.Main) {
                _userPokemons.value = pokemonList
            }
        }
    }
}