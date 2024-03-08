package fr.cpe.pokemon_geo.model.inventory_item

class InventoryItem(
    private var type: InventoryItemType,
    private var quantity: Int
) {
    fun getType(): InventoryItemType {
        return type
    }

    fun getQuantity(): Int {
        return quantity
    }
}