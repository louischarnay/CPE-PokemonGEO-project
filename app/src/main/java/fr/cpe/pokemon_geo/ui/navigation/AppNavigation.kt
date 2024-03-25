package fr.cpe.pokemon_geo.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.ui.screen.fight.Fight
import fr.cpe.pokemon_geo.ui.screen.map.OsmdroidMap
import fr.cpe.pokemon_geo.ui.screen.pokedex.Pokedex
import fr.cpe.pokemon_geo.ui.screen.profile.Profile
import fr.cpe.pokemon_geo.ui.screen.user_inventory.UserInventory
import fr.cpe.pokemon_geo.ui.screen.user_pokemon.UserPokemons

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
            OsmdroidMap(pokemons)
        }

        composable(route = Routes.USER_INVENTORY) {
            UserInventory()
        }

        composable(route = Routes.USER_POKEMON) {
            UserPokemons()
        }

        composable(route = Routes.FIGHT) { backStackEntry ->
            val opponentPokemonId = backStackEntry.arguments?.getInt("opponentPokemonId")
            if (opponentPokemonId == null) {
                navController.popBackStack()
                return@composable
            }
            Fight(opponentPokemonId)
        }
    }
}