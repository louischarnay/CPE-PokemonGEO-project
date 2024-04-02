package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.ui.component.PokemonDetails
import fr.cpe.pokemon_geo.ui.component.PokemonList

@Composable
fun UserPokemons(userPokemonsViewModel: UserPokemonsViewModel = hiltViewModel()) {
    val userPokemons by userPokemonsViewModel.userPokemons.collectAsState()

    val (showPokemonDetails, setShowPokemonDetails) = remember { mutableStateOf(false) }
    var selectedPokemon by remember { mutableStateOf<Pokemon?>(null) }

    PokemonList(
        pokemons = userPokemons,
        onClick = { pokemon ->
            selectedPokemon = pokemon
            setShowPokemonDetails(true)
        }
    )

    if (showPokemonDetails && selectedPokemon !== null) {
        PokemonDetails(pokemon = selectedPokemon!!, onClose = { setShowPokemonDetails(false) })
    }
}
