package fr.cpe.pokemon_geo.database.user_pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val USER_POKEMON_TABLE_NAME = "UserPokemon"

@Entity(tableName = USER_POKEMON_TABLE_NAME)
data class UserPokemonEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "pokemon_order") val pokemonOrder: Int,
    @ColumnInfo(name = "hp_max") val hpMax: Int,
    @ColumnInfo(name = "hp_lost") val hpLost: Int = 0,
    @ColumnInfo(name = "attack") val attack: Int,
    @ColumnInfo(name = "created_at") val createdAt: Long = System.currentTimeMillis(),
)