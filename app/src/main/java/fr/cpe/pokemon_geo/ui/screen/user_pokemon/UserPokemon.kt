package fr.cpe.pokemon_geo.ui.screen.user_pokemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.ui.component.PokemonList

@Composable
fun UserPokemon(userPokemonViewModel: UserPokemonViewModel = hiltViewModel()) {
    // Create a mutable state for the profile
    val pokemons = remember { mutableStateOf<List<Pokemon>>(mutableListOf()) }

    val lifecycleOwner = LocalLifecycleOwner.current

    // Run coroutine when the profileViewModel changes
    LaunchedEffect(key1 = userPokemonViewModel) {
        // Observe the LiveData from the ViewModel
        userPokemonViewModel.userPokemonLiveData.observe(lifecycleOwner) { observedPokemons ->
            pokemons.value = observedPokemons
        }
    }

    PokemonList(pokemons = pokemons.value)
}
