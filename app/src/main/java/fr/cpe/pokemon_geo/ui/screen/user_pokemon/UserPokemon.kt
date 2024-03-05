package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.ui.screen.pokemon.PokemonList

@Composable
fun UserPokemon(userPokemonViewModel: UserPokemonViewModel = hiltViewModel()) {
    val pokemons = userPokemonViewModel.pokemons

    PokemonList(pokemons = pokemons)
}
