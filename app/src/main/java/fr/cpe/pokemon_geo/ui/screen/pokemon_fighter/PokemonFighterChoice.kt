package fr.cpe.pokemon_geo.ui.screen.pokemon_fighter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats
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
            if (pokemon is PokemonWithStats) {
                if (pokemon.getCurrentHP() <= 0) {
                    pokemonFighterChoiceViewModel.showNoHpToast()
                    return@PokemonList
                }
                navController.navigate(Screen.Fight.withArgs(pokemon.getId().toString(), opponentPokemonId.toString()))
            }
        }
    )
}