package fr.cpe.pokemon_geo.ui.screen.fight

import android.app.Application
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
import fr.cpe.pokemon_geo.usecase.GeneratePokemonsUseCase
import fr.cpe.pokemon_geo.utils.buildPokemonWithStatsFromOrder
import fr.cpe.pokemon_geo.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FightViewModel @Inject constructor(
    private val application: Application,
    private val repository: PokemonGeoRepository,
    private val generatePokemonsUseCase: GeneratePokemonsUseCase
): ViewModel() {

    private val _fight = MutableStateFlow<Fight?>(null)
    val fight: StateFlow<Fight?> = _fight

    private val _userCurrentHP = MutableStateFlow(100)
    val userCurrentHP: StateFlow<Int> = _userCurrentHP

    private val _opponentCurrentHP = MutableStateFlow(100)
    val opponentCurrentHP: StateFlow<Int> = _opponentCurrentHP

    fun initFight(
        userPokemonId: Int,
        opponentPokemonId: Int,
        navController: NavController
    ) {
        viewModelScope.launch {
            try {
                val userPokemon = repository.getUserPokemonById(userPokemonId)
                val opponentPokemon = repository.getGeneratedPokemonById(opponentPokemonId)

                val json = application.resources.openRawResource(R.raw.pokemons).bufferedReader().readText()
                val userPokemonData = buildPokemonWithStatsFromOrder(
                    json,
                    userPokemon.pokemonOrder,
                    userPokemonId,
                    userPokemon.hpMax,
                    userPokemon.hpLost,
                    userPokemon.attack
                )
                val opponentPokemonData = buildPokemonWithStatsFromOrder(
                    json,
                    opponentPokemon.pokemonOrder,
                    opponentPokemonId,
                    opponentPokemon.hpMax,
                    0,
                    opponentPokemon.attack
                )

                _fight.value = Fight(userPokemonData, opponentPokemonData)
                _userCurrentHP.value = fight.value?.getUserPokemon()?.getCurrentHP() ?: 0
                _opponentCurrentHP.value = fight.value?.getOpponentPokemon()?.getCurrentHP() ?: 0
            } catch (e: Exception) {
                navController.navigate(Screen.Map.route)
            }
        }
    }

    fun attack(navController: NavController) {
        viewModelScope.launch {
            fight.value?.attack()
            decreaseOpponentPokemonHPWithDelay(fight.value?.getOpponentPokemon()?.getCurrentHP() ?: 0)

            withContext(Dispatchers.Main) {
                checkFightStatus(navController)
            }

            delay(1000)
            fight.value?.opponentPlay()
            decreaseUserPokemonHPWithDelay(fight.value?.getUserPokemon()?.getCurrentHP() ?: 0)

            fight.value?.getUserPokemon() ?.let { repository.updateUserPokemonHpLost(it.getId(), it.getHPLoss()) }
        }
    }

    fun showInventory(navController: NavController) {
        navController.navigate(Screen.FightCapture.route)
    }

    fun end(navController: NavController) {
        viewModelScope.launch {
            fight.value?.getOpponentPokemon()?.getId()?.let { repository.removeGeneratedPokemon(it) }
        }
        navController.navigate(Screen.Map.route)
        generatePokemonsUseCase.start()
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
        } else {
            showToast(application, application.getString(R.string.defeat))
        }
        end(navController)
    }

    fun capture(pokeBallName: String, navController: NavController) {
        viewModelScope.launch { repository.useOneInventoryItem(pokeBallName) }

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

    private suspend fun decreaseUserPokemonHPWithDelay(amountTarget: Int) {
        while (_userCurrentHP.value > amountTarget) {
            delay(5)
            _userCurrentHP.value--
        }
    }

    private suspend fun decreaseOpponentPokemonHPWithDelay(amountTarget: Int) {
        while (_opponentCurrentHP.value > amountTarget) {
            delay(5)
            _opponentCurrentHP.value--
        }
    }
}