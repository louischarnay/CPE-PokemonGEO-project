package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.foundation.clickable
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(pokedexViewModel.pokemons.size) { index ->
            PokedexItem(pokedexViewModel.pokemons[index])
        }
    }
}
