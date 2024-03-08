package fr.cpe.pokemon_geo.ui.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fr.cpe.pokemon_geo.model.inventory_item.InventoryItem
@Composable
fun InventoryListItem(inventory: InventoryItem) {
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ){
        InventoryListImage(inventory)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = inventory.getName(),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Quantité: ${inventory.getQuantity()}",
            )
        }
    }
}

@Composable
fun InventoryListImage(inventory: InventoryItem) {
    Image(
        painter = painterResource(id = inventory.getFrontResource()),
        contentDescription = "Pokemon image",
        modifier = Modifier
            .padding(5.dp)
            .size(60.dp)
    )
}