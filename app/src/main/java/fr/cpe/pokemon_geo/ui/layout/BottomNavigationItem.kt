package fr.cpe.pokemon_geo.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import fr.cpe.pokemon_geo.ui.navigation.Screen

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        label = "Profil",
        icon = Icons.Filled.AccountCircle,
        route = Screen.Profile.route
    ),
    BottomNavigationItem(
        label = "Carte",
        icon = Icons.Filled.LocationOn,
        route = Screen.Map.route
    ),
    BottomNavigationItem(
        label = "Pokédex",
        icon = Icons.Filled.List,
        route = Screen.Pokedex.route
    )
)

fun shouldShowBottomNavigation(route: String?): Boolean {
    return bottomNavigationItems.any { it.route == route }
}

fun getItemIndexFromRoute(route: String?): Int {
    if (!shouldShowBottomNavigation(route)) return 0
    return bottomNavigationItems.indexOfFirst { it.route == route }
}