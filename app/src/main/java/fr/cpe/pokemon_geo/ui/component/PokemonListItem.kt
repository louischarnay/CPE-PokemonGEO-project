package fr.cpe.pokemon_geo.ui.component

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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.model.pokemon.PokemonType
import fr.cpe.pokemon_geo.model.user_pokemon.UserPokemon

@Composable
fun PokemonListItem(pokemon: Pokemon) {
    val (showPokemonDetails, setShowPokemonDetails) = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 2.dp)
            .clickable {
                setShowPokemonDetails(true)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        PokemonListImage(pokemon)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            PokemonData(pokemon)
        }
        if(pokemon !is UserPokemon) Text("#${pokemon.getOrder()}")
    }
    if(showPokemonDetails) PokemonDetails(pokemon = pokemon, onClose = { setShowPokemonDetails(false) })
}

@Composable
fun PokemonListImage(pokemon: Pokemon) {
    Image(
        painter = painterResource(id = pokemon.getFrontResource()),
        contentDescription = "Pokemon image",
        colorFilter = if (pokemon.isUnknownPokemon()) ColorFilter.tint(Color.Black) else null,
        modifier = Modifier
            .padding(5.dp)
            .size(60.dp)
    )
}

@Composable
fun PokemonData(pokemon: Pokemon) {
    Text(
        text = if (pokemon.isUnknownPokemon()) "????" else pokemon.getName(),
        fontWeight = FontWeight.Bold,
    )

    if (pokemon is UserPokemon) {
        PokemonStats(pokemon)
    } else {
        Spacer(modifier = Modifier.height(5.dp))
        PokemonTypes(pokemon)
    }
}

@Composable
fun PokemonType(type: PokemonType?, unknownPokemon: Boolean) {
    if (type == null) return
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Image(
            painter = painterResource(id = type.getFrontResource()),
            contentDescription = "Pokemon type logo",
            colorFilter = if (unknownPokemon) ColorFilter.tint(Color.Black) else null,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = if (unknownPokemon) "???" else type.getName(),
            fontSize = 12.sp
        )
    }
}

@Composable
fun PokemonTypes(pokemon: Pokemon) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        PokemonType(pokemon.getType1(), pokemon.isUnknownPokemon())
        PokemonType(pokemon.getType2(), pokemon.isUnknownPokemon())
    }
}

@Composable
fun PokemonStats(pokemon: UserPokemon) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            LinearProgressIndicator(
                progress = { pokemon.getHealPoint().toFloat() - pokemon.getHealPointLoss().toFloat() / pokemon.getHealPoint().toFloat() },
                modifier = Modifier.weight(1f),
                color = colorResource(id = R.color.black)
            )
            Text(
                text = "HP: ${pokemon.getHealPoint() - pokemon.getHealPointLoss()}/${pokemon.getHealPoint()}",
                fontSize = 12.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            LinearProgressIndicator(
                progress = { pokemon.getAttack().toFloat() / 100 },
                modifier = Modifier.weight(1f),
                color = colorResource(id = R.color.black)
            )
            Text(
                text = "Attack: ${pokemon.getAttack()}",
                fontSize = 12.sp,
            )
        }
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