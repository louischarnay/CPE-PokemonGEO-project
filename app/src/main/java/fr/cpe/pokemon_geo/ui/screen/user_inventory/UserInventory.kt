package fr.cpe.pokemon_geo.ui.screen.user_inventory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.ui.component.InventoryList

@Composable
fun UserInventory(userInventoryViewModel: UserInventoryViewModel = hiltViewModel()) {
    val userInventory by userInventoryViewModel.userInventory.collectAsState()

    InventoryList(objects = userInventory)
}