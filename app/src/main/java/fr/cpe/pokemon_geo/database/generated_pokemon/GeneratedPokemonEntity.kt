package fr.cpe.pokemon_geo.database.generated_pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val GENERATED_POKEMON_TABLE_NAME = "GeneratedPokemon"

@Entity(tableName = GENERATED_POKEMON_TABLE_NAME)
data class GeneratedPokemonEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "level") val level: Long,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
)