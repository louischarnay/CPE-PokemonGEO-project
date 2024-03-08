package fr.cpe.pokemon_geo.database.user_inventory

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
}