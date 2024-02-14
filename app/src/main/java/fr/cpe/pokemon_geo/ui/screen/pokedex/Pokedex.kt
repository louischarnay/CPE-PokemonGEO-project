package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true)
@Composable
fun Pokedex(pokedexViewModel: PokedexViewModel = viewModel()) {
    val pokemons = pokedexViewModel.getPokemons()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pokemons.size) { index ->
                PokedexItem(pokemons[index])
            }
        }
    }
}
