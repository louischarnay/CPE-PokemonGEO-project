package fr.cpe.pokemon_geo.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.cpe.pokemon_geo.model.inventory_item.InventoryItem

@Composable
fun InventoryList(objects: List<InventoryItem>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(objects) { _, inventory ->
            InventoryListItem(inventory)
        }
    }
}