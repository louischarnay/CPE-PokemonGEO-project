package fr.cpe.pokemon_geo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.cpe.pokemon_geo.ui.screen.map.OsmdroidMapView
import fr.cpe.pokemon_geo.ui.screen.pokedex.Pokedex

@Composable
fun AppNavigation(navController: NavController, modifier: Modifier) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.POKEDEX,
        modifier = modifier
    ) {
        composable(route = Routes.POKEDEX) {
            Pokedex()
        }

        composable(route = Routes.MAP) {
            OsmdroidMapView()
        }
    }
}