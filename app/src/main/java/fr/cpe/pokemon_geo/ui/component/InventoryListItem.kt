package fr.cpe.pokemon_geo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.inventory_item.InventoryItem

@Composable
fun InventoryListItem(inventoryItem: InventoryItem, onClick: (InventoryItem) -> Unit = {}) {
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 2.dp)
            .clickable { onClick(inventoryItem) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ){
        InventoryListImage(inventoryItem)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = inventoryItem.getType().getName(),
                fontWeight = FontWeight.Bold,
            )
            Text(text = String.format(stringResource(R.string.item_quantity, inventoryItem.getQuantity())))
        }
    }
}

@Composable
fun InventoryListImage(inventory: InventoryItem) {
    Image(
        painter = painterResource(id = inventory.getType().getFrontResource()),
        contentDescription = "Pokemon image",
        modifier = Modifier
            .padding(5.dp)
            .size(60.dp)
    )
}