package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val repository: PokemonGeoRepository
) : ViewModel() {
    fun updatePokedex(pokemons: List<Pokemon>){
        viewModelScope.launch(Dispatchers.IO) {
            for (pokemon in pokemons) {
                if (repository.getUserPokemonByPokemonId(pokemon.getOrder()) == null) {
                    pokemon.setUnknownPokemon()
                }
            }
        }
    }
}