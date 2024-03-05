package fr.cpe.pokemon_geo.ui.screen.pokemon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.cpe.pokemon_geo.model.Pokemon

@Composable
fun PokemonList(pokemons: List<Pokemon>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(pokemons) { _, pokemon ->
            PokedexListItem(pokemon)
        }
    }
}