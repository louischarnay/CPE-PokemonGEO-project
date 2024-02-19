package fr.cpe.pokemon_geo.database

import fr.cpe.pokemon_geo.database.profile.ProfileEntity
import fr.cpe.pokemon_geo.database.profile.ProfileDao

class PokemonGeoRepository(private val profileDao: ProfileDao) {

    suspend fun getProfile() = profileDao.getProfile()
    suspend fun insertProfile(profileEntity: ProfileEntity) = profileDao.insert(profileEntity)
}