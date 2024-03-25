package fr.cpe.pokemon_geo.ui.screen.fight

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.model.fight.Fight
import fr.cpe.pokemon_geo.model.fight.PokemonFighter
import javax.inject.Inject

@HiltViewModel
class FightViewModel @Inject constructor(): ViewModel() {

    private lateinit var fight: Fight

    fun initFight(
        myPokemon: PokemonFighter,
        opponentPokemon: PokemonFighter
    ) {
        fight = Fight(myPokemon, opponentPokemon)
    }

    fun attack() {
        fight.attack()
        fight.opponentAttack()
    }

    fun escape(): Boolean {
        return fight.opponentEscape()
    }
}