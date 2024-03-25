package fr.cpe.pokemon_geo.ui.screen.fight

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.fight.Fight
import fr.cpe.pokemon_geo.model.fight.PokemonFighter
import javax.inject.Inject

@HiltViewModel
class FightViewModel @Inject constructor(
    private val repository: PokemonGeoRepository
): ViewModel() {

    private lateinit var fight: Fight

    fun initFight(
        myPokemonId: Int,
        opponentPokemonId: Int,
    ) {
        //fight = Fight(myPokemon, opponentPokemon)
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