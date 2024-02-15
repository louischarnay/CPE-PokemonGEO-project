package fr.cpe.pokemon_geo.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import fr.cpe.pokemon_geo.ui.navigation.Routes

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Pokédex",
                icon = Icons.Filled.Home,
                route = Routes.POKEDEX
            ),
            BottomNavigationItem(
                label = "Carte",
                icon = Icons.Filled.LocationOn,
                route = Routes.MAP
            ),
        )
    }
}