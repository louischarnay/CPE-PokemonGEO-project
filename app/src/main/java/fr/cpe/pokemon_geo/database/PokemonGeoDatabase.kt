package fr.cpe.pokemon_geo.database

import androidx.room.Database
import androidx.room.RoomDatabase
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

const val POKEMON_GEO_DATABASE_NAME = "pokemon_geo_database"

@Database(entities = [ProfileEntity::class, UserInventoryEntity::class, UserPokemonEntity::class, GeneratedPokemonEntity::class, PokestopEmptyEntity::class], version = 1)
abstract class PokemonGeoDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun userInventoryDao(): UserInventoryDao
    abstract fun userPokemonDao(): UserPokemonDao
    abstract fun generatedPokemonDao(): GeneratedPokemonDao
    abstract fun pokestopEmptyDao(): PokestopEmptyDao
}