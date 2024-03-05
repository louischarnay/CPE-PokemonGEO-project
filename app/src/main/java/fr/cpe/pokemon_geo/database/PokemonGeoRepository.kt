package fr.cpe.pokemon_geo.database

import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonDao
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.database.profile.ProfileDao
import fr.cpe.pokemon_geo.database.profile.ProfileEntity
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonDao
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonEntity

class PokemonGeoRepository(
    private val profileDao: ProfileDao,
    private val userPokemonDao: UserPokemonDao,
    private val generatedPokemonDao: GeneratedPokemonDao
) {

    suspend fun getProfile() = profileDao.getProfile()
    suspend fun insertProfile(profileEntity: ProfileEntity) = profileDao.insert(profileEntity)
    suspend fun getAllUserPokemon() = userPokemonDao.getAll()
    suspend fun insertUserPokemon(userPokemonEntity: UserPokemonEntity) = userPokemonDao.insert(userPokemonEntity)
    suspend fun getAllGeneratedPokemon() = generatedPokemonDao.getAll()
    suspend fun insertGeneratedPokemon(generatedPokemonEntity: GeneratedPokemonEntity) = generatedPokemonDao.insert(generatedPokemonEntity)
    suspend fun removeGeneratedPokemon(generatedPokemonEntity: GeneratedPokemonEntity) = generatedPokemonDao.delete(generatedPokemonEntity)
}