package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.runtime.Composable
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.ui.component.PokemonList

@Composable
fun Pokedex(pokemons: List<Pokemon>) {
    PokemonList(pokemons)
}
