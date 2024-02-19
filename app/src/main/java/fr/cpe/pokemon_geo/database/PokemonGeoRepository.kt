package fr.cpe.pokemon_geo.database

import fr.cpe.pokemon_geo.database.profile.Profile
import fr.cpe.pokemon_geo.database.profile.ProfileDao

class PokemonGeoRepository(private val profileDao: ProfileDao) {

    fun getProfile() = profileDao.getProfile()
    fun insertProfile(profile: Profile) = profileDao.insert(profile)
}