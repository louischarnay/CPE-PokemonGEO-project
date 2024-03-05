package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import androidx.lifecycle.ViewModel
import fr.cpe.pokemon_geo.model.Pokemon
import javax.inject.Inject

class UserPokemonViewModel @Inject constructor(): ViewModel(){

    val pokemons: MutableList<Pokemon> by lazy {
        loadPokemonsFromResources()
    }

    private fun loadPokemonsFromResources(): MutableList<Pokemon> {
        val pokemons = mutableListOf<Pokemon>()

        pokemons.add(Pokemon(1, "Bulbizarre", "p1", "plante", "poison"))

        return pokemons
    }
}