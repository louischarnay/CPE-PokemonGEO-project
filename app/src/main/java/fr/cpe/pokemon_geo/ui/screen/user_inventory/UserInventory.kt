package fr.cpe.pokemon_geo.ui.screen.user_inventory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.model.inventory_item.InventoryItem
import fr.cpe.pokemon_geo.ui.component.InventoryList

@Composable
fun UserInventory(
    onClick: (InventoryItem) -> Unit = {},
    userInventoryViewModel: UserInventoryViewModel = hiltViewModel()
) {
    val userInventory by userInventoryViewModel.userInventory.collectAsState()

    InventoryList(items = userInventory, onClick = onClick)
}