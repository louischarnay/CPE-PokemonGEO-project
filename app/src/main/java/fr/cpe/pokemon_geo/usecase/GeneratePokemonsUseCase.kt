package fr.cpe.pokemon_geo.usecase

import android.util.Log
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.generated_pokemon.GeneratedPokemonEntity
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.utils.ONE_MINUTE_IN_MILLIS
import fr.cpe.pokemon_geo.utils.ONE_SECOND_IN_MILLIS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

class GeneratePokemonsUseCase @Inject constructor(
    private val repository: PokemonGeoRepository,
    private val getLocationUseCase: GetLocationUseCase
) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    companion object {
        private const val UPDATE_DELAY = 5 * ONE_SECOND_IN_MILLIS
        private const val MAX_PER_AREA = 5
        private const val BASE_GENERATION_CHANCE = 0.05
        private const val AREA_RADIUS_IN_METERS = 100.0
        private const val DISAPPEARANCE_DELAY = 3 * ONE_MINUTE_IN_MILLIS
    }

    fun handle(pokemons: List<Pokemon>) {
        coroutineScope.launch {
            while (true) {
                getLocationUseCase.invoke().collect { location ->
                    if (location != null) {
                        val generatedPokemons = repository.getAllGeneratedPokemon()
                        handlePokemonRemoval(generatedPokemons, location)
                        handlePokemonGeneration(pokemons, generatedPokemons, location)
                    }
                }
                delay(UPDATE_DELAY)
            }
        }
    }

    private suspend fun handlePokemonGeneration(
        pokemons: List<Pokemon>,
        generatedPokemons: List<GeneratedPokemonEntity>,
        location: GeoPoint
    ) {
        val generatedPokemonsCount = generatedPokemons.size
        if (generatedPokemonsCount < MAX_PER_AREA) {

            val random = Math.random()
            if (random < BASE_GENERATION_CHANCE) {
                val pokemon = pokemons.random()

                val latitude = location.latitude + Math.random() * 0.0001
                val longitude = location.longitude + Math.random() * 0.0001
                val generatedPokemon = GeneratedPokemonEntity(
                    pokemonId = pokemon.getOrder(),
                    level = 1,
                    latitude = latitude,
                    longitude = longitude,
                )

                repository.insertGeneratedPokemon(generatedPokemon)
                Log.d("POKEMON", "Pokemon generated: ${pokemon.getName()}")
            }
        }
    }

    private suspend fun handlePokemonRemoval(
        generatedPokemons: List<GeneratedPokemonEntity>,
        location: GeoPoint
    ) {
        generatedPokemons.forEach { pokemon ->
            val pokemonLocation = GeoPoint(pokemon.latitude, pokemon.longitude)
            val distance = location.distanceToAsDouble(pokemonLocation)

            if (distance > AREA_RADIUS_IN_METERS) {
                repository.removeGeneratedPokemon(pokemon)
                Log.d("POKEMON", "Pokemon removed(distance): ${pokemon.pokemonId}")
            } else {
                val currentTime = System.currentTimeMillis()
                if (currentTime - pokemon.createdAt > DISAPPEARANCE_DELAY) {
                    repository.removeGeneratedPokemon(pokemon)
                    Log.d("POKEMON", "Pokemon removed(timer): ${pokemon.pokemonId}")
                }
            }
        }
    }


}