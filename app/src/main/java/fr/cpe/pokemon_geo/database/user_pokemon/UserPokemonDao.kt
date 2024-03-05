package fr.cpe.pokemon_geo.database.user_pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserPokemonDao {
    @Query("SELECT * FROM $USER_POKEMON_TABLE_NAME")
    suspend fun getAll(): List<UserPokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userPokemon: UserPokemonEntity)

}