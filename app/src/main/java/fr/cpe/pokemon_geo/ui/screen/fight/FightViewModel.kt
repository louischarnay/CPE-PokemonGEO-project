package fr.cpe.pokemon_geo.ui.screen.fight

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonEntity
import fr.cpe.pokemon_geo.model.fight.Fight
import fr.cpe.pokemon_geo.model.inventory_item.SearchInventoryItemType
import fr.cpe.pokemon_geo.ui.navigation.Screen
import fr.cpe.pokemon_geo.utils.buildPokemonWithStatsFromOrder
import fr.cpe.pokemon_geo.utils.showToast
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FightViewModel @Inject constructor(
    private val application: Application,
    private val repository: PokemonGeoRepository
): ViewModel() {

    private val _fight = mutableStateOf<Fight?>(null)
    val fight: State<Fight?> = _fight

    private val _hasTriedCapture = mutableStateOf(false)
    val hasTriedCapture: State<Boolean> = _hasTriedCapture

    fun initFight(
        userPokemonId: Int,
        opponentPokemonId: Int,
    ) {
        viewModelScope.launch {
            val userPokemon = repository.getUserPokemonById(userPokemonId)
            val opponentPokemon = repository.getGeneratedPokemonById(opponentPokemonId)

            val json = application.resources.openRawResource(R.raw.pokemons).bufferedReader().readText()
            val userPokemonData = buildPokemonWithStatsFromOrder(json, userPokemon.pokemonOrder, userPokemonId, userPokemon.hpMax, userPokemon.hpLost, userPokemon.attack)
            val opponentPokemonData = buildPokemonWithStatsFromOrder(json, opponentPokemon.pokemonOrder, opponentPokemonId, opponentPokemon.hpMax, 0, opponentPokemon.attack)

            _fight.value = Fight(userPokemonData, opponentPokemonData)
        }
    }

    fun attack(navController: NavController) {
        fight.value?.attack()
        fight.value?.opponentPlay()

        viewModelScope.launch {
            fight.value?.getUserPokemon() ?.let { repository.updateUserPokemonHpLost(it.getId(), it.getHPLoss()) }
        }

        checkFightStatus(navController)
    }

    fun showInventory(navController: NavController) {
        navController.navigate(Screen.FightCapture.route)
    }

    fun end(navController: NavController) {
        viewModelScope.launch {
            fight.value?.getOpponentPokemon()?.getId()?.let { repository.removeGeneratedPokemon(it) }
        }
        navController.navigate(Screen.Map.route)
    }

    private fun checkFightStatus(navController: NavController) {
        if (fight.value?.isOver() == false) return

        if (fight.value?.hasOpponentEscaped() == true) {
            val text = String.format(
                application.getString(R.string.opponent_escaped),
                fight.value?.getOpponentPokemon()?.getName()
            )
            showToast(application, text)
        } else if (fight.value?.isUserWinner() == true) {
            showToast(application, application.getString(R.string.victory))
            viewModelScope.launch {  repository.increaseProfileExperience(10) }
            return
        } else {
            showToast(application, application.getString(R.string.defeat))
        }
        end(navController)
    }

    fun capture(pokeBallName: String, navController: NavController) {
        _hasTriedCapture.value = true

        val pokeBall = SearchInventoryItemType.byName(pokeBallName)
        val isCaptured = fight.value?.tryToCapture(pokeBall)
        if (isCaptured == true) {
            val text = String.format(
                application.getString(R.string.pokemon_captured),
                fight.value?.getOpponentPokemon()?.getName()
            )
            showToast(application, text)

            val newUserPokemon = UserPokemonEntity(
                pokemonOrder = fight.value?.getOpponentPokemon()?.getOrder() ?: 0,
                hpMax = fight.value?.getOpponentPokemon()?.getMaxHP() ?: 0,
                hpLost = fight.value?.getOpponentPokemon()?.getHPLoss() ?: 0,
                attack = fight.value?.getOpponentPokemon()?.getAttack() ?: 0
            )
            viewModelScope.launch {
                repository.increaseProfileExperience(10)
                repository.insertUserPokemon(newUserPokemon)
            }
            end(navController)
            return
        }

        val text = String.format(
            application.getString(R.string.pokemon_not_captured),
            fight.value?.getOpponentPokemon()?.getName()
        )
        showToast(application, text)
    }
}