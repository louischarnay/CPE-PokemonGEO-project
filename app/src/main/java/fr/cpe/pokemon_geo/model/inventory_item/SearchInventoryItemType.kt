package fr.cpe.pokemon_geo.model.inventory_item

import fr.cpe.pokemon_geo.R

object SearchInventoryItemType {
    private val pokeball = InventoryItemType(
        name = INVENTORY_ITEM.pokeball.name.replaceFirstChar { it.uppercase() },
        frontResource = R.drawable.pokeball,
    )

    private val masterball = InventoryItemType(
        name = INVENTORY_ITEM.masterball.name.replaceFirstChar { it.uppercase() },
        frontResource = R.drawable.masterball,
    )

    fun byName(name: String): InventoryItemType {
        return when (name.lowercase()) {
            INVENTORY_ITEM.pokeball.name -> pokeball
            INVENTORY_ITEM.masterball.name -> masterball
            else -> throw IllegalArgumentException("Unknown object type: $name")
        }
    }
}