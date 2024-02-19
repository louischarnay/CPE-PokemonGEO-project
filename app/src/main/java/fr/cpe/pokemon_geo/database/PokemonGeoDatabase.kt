package fr.cpe.pokemon_geo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.cpe.pokemon_geo.database.profile.Profile
import fr.cpe.pokemon_geo.database.profile.ProfileDao

const val POKEMON_GEO_DATABASE_NAME = "pokemon_geo_database"

@Database(entities = [Profile::class], version = 1)
abstract class PokemonGeoDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao

}