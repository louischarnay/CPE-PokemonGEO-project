package fr.cpe.pokemon_geo.database.pokestop_empty

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val POKESTOP_EMPTY_TABLE_NAME = "PokestopEmpty"

@Entity(tableName = POKESTOP_EMPTY_TABLE_NAME)
data class PokestopEmptyEntity(
    @PrimaryKey @ColumnInfo val id: String,
    @ColumnInfo val latitude: Double,
    @ColumnInfo val longitude: Double,
    @ColumnInfo val created_at: Long = System.currentTimeMillis(),
)
