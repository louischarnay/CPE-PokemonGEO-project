package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Pokedex(pokedexViewModel: PokedexViewModel = hiltViewModel()) {
    val pokemons = pokedexViewModel.pokemons

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(pokemons) { _, pokemon ->
            PokedexItem(pokemon)
        }
    }
}
