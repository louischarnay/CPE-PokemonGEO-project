package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.ui.screen.pokemon.PokemonList

@Composable
fun Pokedex(pokedexViewModel: PokedexViewModel = hiltViewModel()) {
    val pokemons = pokedexViewModel.pokemons

    PokemonList(pokemons = pokemons)
}
