package fr.cpe.pokemon_geo.ui.screen.pokemon_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.POKEMON_TYPE
import fr.cpe.pokemon_geo.model.Pokemon

@Composable
fun PokemonCard(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonImage(pokemon)
        Text(
            text = pokemon.name + " #" + pokemon.order,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 25.sp,
            modifier = Modifier.padding(15.dp)
        )
        Row {
            PokemonType(pokemon.getType1())
            PokemonType(pokemon.getType2())
        }
    }
}

@Composable
fun PokemonImage(pokemon: Pokemon) {
    Image(
        painter = painterResource(id = pokemon.frontResource),
        contentDescription = null,
        modifier = Modifier
            .size(380.dp)
            .padding(5.dp)
    )
}

@Composable
fun PokemonType(type: POKEMON_TYPE?) {
    if (type == null) return
    Row (
        modifier = Modifier.padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.feu),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
        Text(type.name)
    }

}