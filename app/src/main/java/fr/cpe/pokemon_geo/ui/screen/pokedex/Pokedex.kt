package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.runtime.Composable
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.ui.screen.pokemon.PokemonList

@Composable
fun Pokedex(pokemons: List<Pokemon>) {
    PokemonList(pokemons)
}
