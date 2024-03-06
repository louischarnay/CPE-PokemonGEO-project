package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonEntity
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.utils.loadPokemonFromId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserPokemonViewModel @Inject constructor(
    private val application: Application,
    private val repository: PokemonGeoRepository
): ViewModel(){

    private val _userPokemonLiveData = MutableLiveData<List<Pokemon>>()
    val userPokemonLiveData: LiveData<List<Pokemon>> = _userPokemonLiveData

    init {
        createDefaultList()
        fetchUserPokemons()
    }

    private fun createDefaultList() {
        val userPokemonEntity = UserPokemonEntity(pokemonId = 12, createdAt = System.currentTimeMillis())
        viewModelScope.launch {
            repository.insertUserPokemon(userPokemonEntity)
        }
    }

    private fun fetchUserPokemons() {
        viewModelScope.launch {
            val pokemonList = mutableListOf<Pokemon>()
            val userPokemons = repository.getAllUserPokemon()

            userPokemons.forEach {
                pokemonList.add(loadPokemonFromId(application.resources.openRawResource(R.raw.pokemons), it.pokemonId))
            }

            withContext(Dispatchers.Main) {
                _userPokemonLiveData.value = pokemonList
            }
        }
    }
}