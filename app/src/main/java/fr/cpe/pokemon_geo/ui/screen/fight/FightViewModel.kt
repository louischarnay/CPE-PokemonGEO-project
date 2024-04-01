package fr.cpe.pokemon_geo.ui.screen.fight

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.fight.Fight
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
        fight.attack()
        fight.opponentAttack()
    }

    fun escape(): Boolean {
        return fight.opponentEscape()
    }
}