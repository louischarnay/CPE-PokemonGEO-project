package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.profile.ProfileEntity
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonEntity
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.utils.loadPokemonFromId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.sql.Date
import javax.inject.Inject

@HiltViewModel
class UserPokemonViewModel @Inject constructor(
    private val application: Application,
    private val repository: PokemonGeoRepository
): ViewModel(){

    private val _userPokemonsLiveData = MutableLiveData<List<Pokemon>>()
    val userPokemonsLiveData: LiveData<List<Pokemon>> = _userPokemonsLiveData

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
                _userPokemonsLiveData.value = pokemonList
            }
        }
    }
}