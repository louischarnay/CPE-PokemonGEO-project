package fr.cpe.pokemon_geo.database

import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonDao
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.database.profile.ProfileDao
import fr.cpe.pokemon_geo.database.profile.ProfileEntity

class PokemonGeoRepository(
    private val profileDao: ProfileDao,
    private val generatedPokemonDao: GeneratedPokemonDao
) {

    suspend fun getProfile() = profileDao.getProfile()
    suspend fun insertProfile(profileEntity: ProfileEntity) = profileDao.insert(profileEntity)

    suspend fun getAllGeneratedPokemon() = generatedPokemonDao.getAll()
    suspend fun insertGeneratedPokemon(generatedPokemonEntity: GeneratedPokemonEntity) = generatedPokemonDao.insert(generatedPokemonEntity)
    suspend fun deleteGeneratedPokemon(generatedPokemonEntity: GeneratedPokemonEntity) = generatedPokemonDao.delete(generatedPokemonEntity)
}