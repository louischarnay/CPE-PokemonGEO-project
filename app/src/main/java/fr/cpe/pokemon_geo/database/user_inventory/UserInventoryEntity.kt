package fr.cpe.pokemon_geo.database.user_inventory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val USER_INVENTORY_TABLE_NAME = "UserInventory"

@Entity(tableName = USER_INVENTORY_TABLE_NAME)
data class UserInventoryEntity(
    @PrimaryKey @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
)