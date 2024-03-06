package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.ui.component.PokemonList

@Composable
fun UserPokemons(userPokemonsViewModel: UserPokemonsViewModel = hiltViewModel()) {
    val userPokemons by userPokemonsViewModel.userPokemons.collectAsState()

    PokemonList(pokemons = userPokemons)
}
