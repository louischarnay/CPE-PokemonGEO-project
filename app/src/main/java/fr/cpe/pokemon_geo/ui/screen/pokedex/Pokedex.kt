package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.ui.component.PokemonDetails
import fr.cpe.pokemon_geo.ui.component.PokemonList

@Composable
fun Pokedex(pokemons: List<Pokemon>, pokedexViewModel: PokedexViewModel = hiltViewModel()) {
    pokedexViewModel.updatePokedex(pokemons)

    val (showPokemonDetails, setShowPokemonDetails) = remember { mutableStateOf(false) }
    var selectedPokemon by remember { mutableStateOf<Pokemon?>(null) }

    PokemonList(
        pokemons = pokemons,
        onClick = { pokemon ->
            selectedPokemon = pokemon
            setShowPokemonDetails(true)
        }
    )

    if (showPokemonDetails && selectedPokemon !== null) {
        PokemonDetails(pokemon = selectedPokemon!!, onClose = { setShowPokemonDetails(false) })
    }
}
