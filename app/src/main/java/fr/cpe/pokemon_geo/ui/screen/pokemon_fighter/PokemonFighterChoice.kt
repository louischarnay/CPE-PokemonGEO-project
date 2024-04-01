package fr.cpe.pokemon_geo.ui.screen.pokemon_fighter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.cpe.pokemon_geo.ui.component.PokemonList
import fr.cpe.pokemon_geo.ui.navigation.Screen

@Composable
fun PokemonFighterChoice(
    navController: NavController,
    opponentPokemonId: Int,
    pokemonFighterChoiceViewModel: PokemonFighterChoiceViewModel = hiltViewModel()
) {
    val userPokemons by pokemonFighterChoiceViewModel.userPokemons.collectAsState()

    PokemonList(
        pokemons = userPokemons,
        onClick = { pokemon ->
            navController.navigate(Screen.Fight.withArgs(opponentPokemonId, pokemon.getOrder()))
        }
    )
}