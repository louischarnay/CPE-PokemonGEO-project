package fr.cpe.pokemon_geo.ui.screen.starter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonEntity
import fr.cpe.pokemon_geo.model.Pokemon
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarterViewModel @Inject constructor(
    private val repository: PokemonGeoRepository,
): ViewModel() {

    fun chooseStarter(pokemon: Pokemon) {
        viewModelScope.launch {
            val newPokemon = UserPokemonEntity(pokemonId = pokemon.getOrder())
            repository.insertUserPokemon(newPokemon)
        }
    }
}