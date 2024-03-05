package fr.cpe.pokemon_geo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.ui.screen.map.OsmdroidMap
import fr.cpe.pokemon_geo.ui.screen.pokedex.Pokedex
import fr.cpe.pokemon_geo.ui.screen.profile.Profile

@Composable
fun AppNavigation(navController: NavController, pokemons: List<Pokemon>, modifier: Modifier) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.PROFILE,
        modifier = modifier
    ) {
        composable(route = Routes.PROFILE) {
            Profile()
        }

        composable(route = Routes.POKEDEX) {
            Pokedex(pokemons)
        }

        composable(route = Routes.MAP) {
            OsmdroidMap()
        }
    }
}