package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.model.PokemonType

@Composable
fun PokedexItem(pokemon: Pokemon) {
    Row(modifier = Modifier.fillMaxSize().padding(horizontal = 15.dp, vertical = 2.dp)) {
        PokemonImage(pokemon)
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(pokemon.getName())
            Row {
                PokemonType(pokemon.getType1())
                PokemonType(pokemon.getType2())
            }
        }
        Text("#${pokemon.getOrder()}")
    }
}

@Composable
fun PokemonImage(pokemon: Pokemon) {
    Image(
        painter = painterResource(id = pokemon.getFrontResource()),
        contentDescription = null,
        modifier = Modifier.padding(5.dp).size(60.dp)
    )
}

@Composable
fun PokemonType(type: PokemonType?) {
    if (type == null) return
    Row {
        Image(
            painter = painterResource(id = type.getFrontResource()),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(type.getName())
    }
}