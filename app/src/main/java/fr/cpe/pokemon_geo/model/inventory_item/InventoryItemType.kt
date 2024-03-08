package fr.cpe.pokemon_geo.model.inventory_item

class InventoryItemType(
    private var name: String,
    private var frontResource: Int
) {

    fun getName(): String {
        return name
    }

    fun getFrontResource(): Int {
        return frontResource
    }
}