package fr.cpe.pokemon_geo.ui.screen.fight

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.fight.Fight
import fr.cpe.pokemon_geo.ui.navigation.Screen
import fr.cpe.pokemon_geo.utils.buildPokemonWithStatsFromOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FightViewModel @Inject constructor(
    private val application: Application,
    private val repository: PokemonGeoRepository
): ViewModel() {

    private val _fight = MutableStateFlow<Fight?>(null)
    val fight: StateFlow<Fight?> = _fight

    fun initFight(
        userPokemonId: Int,
        opponentPokemonId: Int,
    ) {
        viewModelScope.launch {
            val userPokemon = repository.getUserPokemonById(userPokemonId)
            val opponentPokemon = repository.getGeneratedPokemonById(opponentPokemonId)

            val userPokemonData = buildPokemonWithStatsFromOrder(application.resources.openRawResource(R.raw.pokemons), userPokemon.pokemonOrder, userPokemonId, userPokemon.hpMax, userPokemon.hpLost, userPokemon.attack)
            val opponentPokemonData = buildPokemonWithStatsFromOrder(application.resources.openRawResource(R.raw.pokemons), opponentPokemon.pokemonOrder, opponentPokemonId, opponentPokemon.hpMax, 0, opponentPokemon.attack)

            _fight.value = Fight(userPokemonData, opponentPokemonData)
        }
    }

    fun attack() {
        Log.e("FightViewModel", "attack")
    }

    fun showUserPokemons() {
        Log.e("FightViewModel", "showUserPokemons")
    }

    fun showInventory(navController: NavController) {
        navController.navigate(Screen.UserInventory.route)
    }

    fun escape(navController: NavController) {
        viewModelScope.launch {
            repository.removeGeneratedPokemon(fight.value?.getOpponentPokemon()?.getId() ?: 0)
        }
        navController.navigate(Screen.Map.route)
    }
}