package fr.cpe.pokemon_geo.usecase

import android.util.Log
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class GeneratePokemonsUseCase @Inject constructor(
    private val repository: PokemonGeoRepository
) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun generatePokemons(pokemons: List<Pokemon>) {
        coroutineScope.launch {
            while (true) {
                Log.d("POKEMON", "Generate a pokemon")
                delay(10_000L)
            }
        }
    }
}