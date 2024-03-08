package fr.cpe.pokemon_geo.model.inventory_item

import fr.cpe.pokemon_geo.R

object SearchInventoryItemType {
    private val pokeball = InventoryItemType(
        name = OBJECT_TYPE.Pokeball.name,
        frontResource = R.drawable.pokeball,
    )

    private val masterball = InventoryItemType(
        name = OBJECT_TYPE.Masterball.name,
        frontResource = R.drawable.masterball,
    )

    fun byName(name: String): InventoryItemType {
        return when (name.lowercase()) {
            "pokeball" -> pokeball
            "masterball" -> masterball
            else -> throw IllegalArgumentException("Unknown object type: $name")
        }
    }
}