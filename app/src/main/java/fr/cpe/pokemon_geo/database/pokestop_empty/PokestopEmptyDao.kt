package fr.cpe.pokemon_geo.database.pokestop_empty

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokestopEmptyDao {
    @Query("SELECT * FROM $POKESTOP_EMPTY_TABLE_NAME WHERE id = :id")
    suspend fun getById(id: String): PokestopEmptyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokestopEmptyEntity: PokestopEmptyEntity)

    @Query("DELETE FROM $POKESTOP_EMPTY_TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: String)
}