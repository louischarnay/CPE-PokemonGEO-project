package fr.cpe.pokemon_geo.database.user_inventory

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserInventoryDao {
    @Query("SELECT * FROM $USER_INVENTORY_TABLE_NAME")
    suspend fun getAll(): List<UserInventoryEntity>

    @Query("SELECT * FROM $USER_INVENTORY_TABLE_NAME WHERE type = :type")
    suspend fun getByType(type: String): UserInventoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userInventoryEntity: UserInventoryEntity)
    suspend fun appendQuantity(type: String, quantity: Int) {
        val existing = getByType(type)

        if (existing != null) {
            insert(UserInventoryEntity(type = type, quantity = existing.quantity + quantity))
        } else {
            insert(UserInventoryEntity(type = type, quantity = quantity))
        }
    }
    suspend fun useOne(type: String) {
        val existing = getByType(type)

        if (existing != null) {
            insert(UserInventoryEntity(type = type, quantity = existing.quantity - 1))
        }
    }
}