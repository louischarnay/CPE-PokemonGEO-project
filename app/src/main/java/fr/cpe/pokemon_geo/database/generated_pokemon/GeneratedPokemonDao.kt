package fr.cpe.pokemon_geo.database.generated_pokemon

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GeneratedPokemonDao {
    @Query("SELECT * FROM $GENERATED_POKEMON_TABLE_NAME")
    suspend fun getAll(): List<GeneratedPokemonEntity>

    @Insert
    suspend fun insert(pokemon: GeneratedPokemonEntity)

    @Delete
    suspend fun delete(pokemon: GeneratedPokemonEntity)
}