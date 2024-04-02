package fr.cpe.pokemon_geo.database.user_pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserPokemonDao {
    @Query("SELECT * FROM $USER_POKEMON_TABLE_NAME")
    suspend fun getAll(): List<UserPokemonEntity>

    @Query("SELECT * FROM $USER_POKEMON_TABLE_NAME WHERE id = :id")
    suspend fun getById(id: Int): UserPokemonEntity

    @Query("SELECT * FROM $USER_POKEMON_TABLE_NAME WHERE pokemon_order = :pokemonOrder")
    suspend fun hasPokemon(pokemonOrder: Int): UserPokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userPokemon: UserPokemonEntity)

    @Query("UPDATE $USER_POKEMON_TABLE_NAME SET hp_lost = 0")
    suspend fun healAllPokemons()
}