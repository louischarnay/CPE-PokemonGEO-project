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
import fr.cpe.pokemon_geo.utils.showToast
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

            fight.value?.getOpponentPokemon()?.getId()?.let { repository.removeGeneratedPokemon(it) }
        }
    }

    fun attack(navController: NavController) {
        fight.value?.attack()
        fight.value?.opponentAttack()

        viewModelScope.launch {
            fight.value?.getUserPokemon() ?.let { repository.updateUserPokemonHpLost(it.getId(), it.getHealPointLoss()) }
        }

        fight.value?.opponentAttack()

        fight.value?.tryToEscapeAsOpponent()
        checkFightStatus(navController)
    }

    fun showUserPokemons() {
        Log.e("FightViewModel", "showUserPokemons")
    }

    fun showInventory(navController: NavController) {
        navController.navigate(Screen.UserInventory.route)
    }

    fun end(navController: NavController) {
        navController.navigate(Screen.Map.route)
    }

    private fun checkFightStatus(navController: NavController) {
        if (fight.value?.isOver() == true) {
            if (fight.value?.isUserWinner() == true) {
                showToast(application, "VICTOIRE")
                viewModelScope.launch {
                    repository.increaseProfileExperience(10)
                }
            } else {
                showToast(application, "DEFAITE")
            }
            end(navController)
        }
    }
}