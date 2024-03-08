package fr.cpe.pokemon_geo.model.inventory_item

class InventoryItem(
    private var name: String,
    frontRessource: Int,
    private var type: String,
    private var quantity: Int
) {
private var frontResource = 0

    init {
        this.frontResource = frontRessource
    }

    fun getName(): String {
        return name
    }

    fun getFrontResource(): Int {
        return frontResource
    }

    fun getType(): String {
        return type
    }

    fun getQuantity(): Int {
        return quantity
    }
}