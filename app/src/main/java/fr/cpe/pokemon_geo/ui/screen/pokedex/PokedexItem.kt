package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.model.PokemonType
import fr.cpe.pokemon_geo.ui.screen.pokemon_card.PokemonCard
import timber.log.Timber

@Composable
fun PokedexItem(pokemon: Pokemon) {
    val (showPokemonDetails, setShowPokemonDetails) = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 2.dp)
            .clickable {
                Timber.d("Pokemon clicked: ${pokemon.getName()}")
                setShowPokemonDetails(true)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        PokemonImage(pokemon)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = pokemon.getName(),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(5.dp))
            PokemonTypes(pokemon)
        }
        Text("#${pokemon.getOrder()}")
    }
    if(showPokemonDetails) PokemonDetails(pokemon = pokemon , onClose = { setShowPokemonDetails(false) })
}

@Composable
fun PokemonImage(pokemon: Pokemon) {
    Image(
        painter = painterResource(id = pokemon.getFrontResource()),
        contentDescription = null,
        modifier = Modifier
            .padding(5.dp)
            .size(60.dp)
    )
}

@Composable
fun PokemonTypes(pokemon: Pokemon) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        PokemonType(pokemon.getType1())
        PokemonType(pokemon.getType2())
    }
}

@Composable
fun PokemonType(type: PokemonType?) {
    if (type == null) return
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Image(
            painter = painterResource(id = type.getFrontResource()),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = type.getName(),
            fontSize = 12.sp
        )
    }
}

@Composable
fun PokemonDetails(pokemon: Pokemon, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        confirmButton = {
            Button(onClick = onClose) {
                Text(text = "Close")
            }
        },
        text = {
            PokemonCard(pokemon)
        }
    )
}