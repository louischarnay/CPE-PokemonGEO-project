package fr.cpe.pokemon_geo.ui.screen.fight

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.fight.Fight
import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FightViewModel @Inject constructor(
    private val repository: PokemonGeoRepository
): ViewModel() {

    private lateinit var fight: Fight

    fun initFight(
        userPokemonId: Int,
        opponentPokemonId: Int,
    ) {
        viewModelScope.launch {
            // fetch pokemons
        }
    }

    fun attack() {
        Log.e("FightViewModel", "attack")
        //fight.attack()
        //fight.opponentAttack()
    }

    fun showUserPokemons() {
        Log.e("FightViewModel", "showUserPokemons")
    }

    fun showInventory() {
        Log.e("FightViewModel", "showInventory")
    }

    fun escape() {
        Log.e("FightViewModel", "escape")
        //return fight.opponentEscape()
    }
}