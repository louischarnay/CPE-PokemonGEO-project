package fr.cpe.pokemon_geo.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.cpe.pokemon_geo.database.POKEMON_GEO_DATABASE_NAME
import fr.cpe.pokemon_geo.database.PokemonGeoDatabase
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonDao
import fr.cpe.pokemon_geo.database.profile.ProfileDao
import fr.cpe.pokemon_geo.database.user_inventory.UserInventoryDao
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PokemonGeoDatabase {
        return Room.databaseBuilder(
            context,
            PokemonGeoDatabase::class.java,
            POKEMON_GEO_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideProfileDao(database: PokemonGeoDatabase): ProfileDao {
        return database.profileDao()
    }

    @Provides
    @Singleton
    fun provideUserInventoryDao(database: PokemonGeoDatabase): UserInventoryDao {
        return database.userInventoryDao()
    }

    @Provides
    @Singleton
    fun provideUserPokemonDao(database: PokemonGeoDatabase): UserPokemonDao {
        return database.userPokemonDao()
    }

    @Provides
    @Singleton
    fun provideGeneratedPokemonDao(database: PokemonGeoDatabase): GeneratedPokemonDao {
        return database.generatedPokemonDao()
    }

    @Provides
    @Singleton
    fun provideRepository(
        profileDao: ProfileDao,
        userInventoryDao: UserInventoryDao,
        userPokemonDao: UserPokemonDao,
        generatedPokemonDao: GeneratedPokemonDao
    ): PokemonGeoRepository {
        return PokemonGeoRepository(profileDao, userInventoryDao, userPokemonDao, generatedPokemonDao)
    }
}