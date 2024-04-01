package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.ui.component.PokemonList

@Composable
fun Pokedex(pokemons: List<Pokemon>, pokedexViewModel: PokedexViewModel = hiltViewModel()) {
    pokedexViewModel.updatePokedex(pokemons)
    PokemonList(pokemons)
}
