package fr.cpe.pokemon_geo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.cpe.pokemon_geo.model.pokemon.Pokemon

@Composable
fun PokemonCard(pokemon: Pokemon) {
    Column(
        modifier = Modifier.padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonCardImage(pokemon)
        Text(
            text = pokemon.getName() + " #" + pokemon.getOrder(),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 25.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        PokemonTypes(pokemon)
    }
}

@Composable
fun PokemonCardImage(pokemon: Pokemon) {
    Image(
        painter = painterResource(id = pokemon.getFrontResource()),
        contentDescription = "Pokemon image",
        modifier = Modifier.size(300.dp)
    )
}
