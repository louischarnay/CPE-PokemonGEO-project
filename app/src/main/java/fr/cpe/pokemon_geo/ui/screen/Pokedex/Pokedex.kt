package fr.cpe.pokemon_geo.ui.screen.Pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.POKEMON_TYPE
import fr.cpe.pokemon_geo.model.Pokemon

@Composable
fun Pokedex(pokemons: List<Pokemon>) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pokemons.size) { index ->
                PokedexItem(pokemons[index])
            }
        }
    }
}

@Composable
fun PokedexItem(pokemon: Pokemon) {
    Row(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
        Column(modifier = Modifier.weight(1f).fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text(pokemon.name)
            Row {
                PokemonType(pokemon.getType1())
                PokemonType(pokemon.getType2())
            }
        }
        Text(text = "#0")
    }
}

@Composable
fun PokemonType(type: POKEMON_TYPE?) {
    if (type == null) return
    Row {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = null, modifier = Modifier.size(20.dp))
        Text(type.name)
    }
}