package fr.cpe.pokemon_geo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonDao
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.database.profile.ProfileDao
import fr.cpe.pokemon_geo.database.profile.ProfileEntity

const val POKEMON_GEO_DATABASE_NAME = "pokemon_geo_database"

@Database(entities = [ProfileEntity::class, GeneratedPokemonEntity::class], version = 1)
abstract class PokemonGeoDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun generatedPokemonDao(): GeneratedPokemonDao

}