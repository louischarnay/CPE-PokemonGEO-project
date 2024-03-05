package fr.cpe.pokemon_geo.database.user_pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val USER_POKEMON_TABLE_NAME = "UserPokemon"

@Entity(tableName = USER_POKEMON_TABLE_NAME)
data class UserPokemonEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
)