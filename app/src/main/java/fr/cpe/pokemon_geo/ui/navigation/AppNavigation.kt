package fr.cpe.pokemon_geo.ui.navigation

import androidx.activity.compose.BackHandler
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
import fr.cpe.pokemon_geo.ui.screen.starter.Starter
import fr.cpe.pokemon_geo.ui.screen.user_pokemon.UserPokemon

@Composable
fun AppNavigation(navController: NavController, pokemons: List<Pokemon>, modifier: Modifier) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.PROFILE,
        modifier = modifier
    ) {
        composable(route = Routes.PROFILE) {
            BackHandler(true) {}
            Profile(navController)
        }

        composable(route = Routes.POKEDEX) {
            BackHandler(true) {}
            Pokedex(pokemons)
        }

        composable(route = Routes.MAP) {
            BackHandler(true) {}
            OsmdroidMap()
        }

        composable(route = Routes.USER_POKEMON) {
            UserPokemon()
        }
    }
}