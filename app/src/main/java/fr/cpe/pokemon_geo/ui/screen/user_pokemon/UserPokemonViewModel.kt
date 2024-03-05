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

        pokemons.add(Pokemon(1, "Bulbasaur", "Grass", "Poison"))
        pokemons.add(Pokemon(2, "Ivysaur", "Grass", "Poison"))
        pokemons.add(Pokemon(3, "Venusaur", "Grass", "Poison"))
        pokemons.add(Pokemon(4, "Charmander", "Fire", ""))
        pokemons.add(Pokemon(5, "Charmeleon", "Fire", ""))
        pokemons.add(Pokemon(6, "Charizard", "Fire", "Flying"))
        pokemons.add(Pokemon(7, "Squirtle", "Water", ""))
        pokemons.add(Pokemon(8, "Wartortle", "Water", ""))
        pokemons.add(Pokemon(9, "Blastoise", "Water", ""))

        return pokemons
    }
}