package fr.cpe.pokemon_geo.database

import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonDao
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.database.profile.ProfileDao
import fr.cpe.pokemon_geo.database.profile.ProfileEntity
import fr.cpe.pokemon_geo.database.user_inventory.UserInventoryDao
import fr.cpe.pokemon_geo.database.user_inventory.UserInventoryEntity
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonDao
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonEntity

class PokemonGeoRepository(
    private val profileDao: ProfileDao,
    private val userInventoryDao: UserInventoryDao,
    private val userPokemonDao: UserPokemonDao,
    private val generatedPokemonDao: GeneratedPokemonDao
) {

    suspend fun getProfile() = profileDao.getProfile()
    suspend fun insertProfile(profileEntity: ProfileEntity) = profileDao.insert(profileEntity)
    suspend fun getUserInventory() = userInventoryDao.getAll()
    suspend fun insertUserInventory(userInventoryEntity: UserInventoryEntity) = userInventoryDao.insert(userInventoryEntity)
    suspend fun appendUserInventoryQuantity(type: String, quantity: Int) = userInventoryDao.appendQuantity(type, quantity)
    suspend fun getAllUserPokemon() = userPokemonDao.getAll()
    suspend fun getUserPokemonById(id: Int) = userPokemonDao.getById(id)
    suspend fun insertUserPokemon(userPokemonEntity: UserPokemonEntity) = userPokemonDao.insert(userPokemonEntity)
    suspend fun healAllUserPokemons() = userPokemonDao.healAllPokemons()
    suspend fun getAllGeneratedPokemon() = generatedPokemonDao.getAll()
    suspend fun insertGeneratedPokemon(generatedPokemonEntity: GeneratedPokemonEntity) = generatedPokemonDao.insert(generatedPokemonEntity)
    suspend fun removeGeneratedPokemon(generatedPokemonEntity: GeneratedPokemonEntity) = generatedPokemonDao.delete(generatedPokemonEntity)
}