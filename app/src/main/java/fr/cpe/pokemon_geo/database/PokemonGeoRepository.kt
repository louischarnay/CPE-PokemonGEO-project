package fr.cpe.pokemon_geo.database

import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonDao
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.database.pokestop_empty.PokestopEmptyDao
import fr.cpe.pokemon_geo.database.pokestop_empty.PokestopEmptyEntity
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
    private val generatedPokemonDao: GeneratedPokemonDao,
    private val pokestopEmptyDao: PokestopEmptyDao,
) {

    suspend fun getProfile() = profileDao.getProfile()
    suspend fun insertProfile(profileEntity: ProfileEntity) = profileDao.insert(profileEntity)
    suspend fun getUserInventory() = userInventoryDao.getAll()
    suspend fun insertUserInventory(userInventoryEntity: UserInventoryEntity) = userInventoryDao.insert(userInventoryEntity)
    suspend fun appendUserInventoryQuantity(type: String, quantity: Int) = userInventoryDao.appendQuantity(type, quantity)
    suspend fun getAllUserPokemon() = userPokemonDao.getAll()
    suspend fun getUserPokemonById(id: Int) = userPokemonDao.getById(id)
    suspend fun hasUserPokemon(pokemonOrder: Int) = userPokemonDao.hasPokemon(pokemonOrder)
    suspend fun insertUserPokemon(userPokemonEntity: UserPokemonEntity) = userPokemonDao.insert(userPokemonEntity)
    suspend fun healAllUserPokemons() = userPokemonDao.healAllPokemons()
    suspend fun getAllGeneratedPokemon() = generatedPokemonDao.getAll()
    suspend fun getGeneratedPokemonById(id: Int) = generatedPokemonDao.getById(id)
    suspend fun insertGeneratedPokemon(generatedPokemonEntity: GeneratedPokemonEntity) = generatedPokemonDao.insert(generatedPokemonEntity)
    suspend fun removeGeneratedPokemon(id: Int) = generatedPokemonDao.remove(id)
    suspend fun getAllPokestopEmpty() = pokestopEmptyDao.getAll()
    suspend fun getPokestopEmptyById(id: String) = pokestopEmptyDao.getById(id)
    suspend fun insertPokestopEmpty(pokestopEmptyEntity: PokestopEmptyEntity) = pokestopEmptyDao.insert(pokestopEmptyEntity)
    suspend fun removePokestopEmptyById(id: String) = pokestopEmptyDao.remove(id)
}