package fr.cpe.pokemon_geo.database.generated_pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GeneratedPokemonDao {
    @Query("SELECT * FROM $GENERATED_POKEMON_TABLE_NAME")
    suspend fun getAll(): List<GeneratedPokemonEntity>

    @Query("SELECT * FROM $GENERATED_POKEMON_TABLE_NAME WHERE id = :id")
    suspend fun getById(id: Int): GeneratedPokemonEntity

    @Insert
    suspend fun insert(pokemon: GeneratedPokemonEntity)

    @Query("DELETE FROM $GENERATED_POKEMON_TABLE_NAME WHERE id = :id")
    suspend fun remove(id: Int)
}