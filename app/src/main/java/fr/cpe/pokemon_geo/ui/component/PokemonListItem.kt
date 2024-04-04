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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats

@Composable
fun PokemonListItem(pokemon: Pokemon, onClick: (Pokemon) -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 2.dp)
            .clickable { onClick(pokemon) },
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
        if(pokemon !is PokemonWithStats) Text("#${pokemon.getOrder()}")
    }
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

    if (pokemon is PokemonWithStats) {
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
fun PokemonStats(pokemon: PokemonWithStats) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            LinearProgressIndicator(
                progress = { pokemon.getCurrentHP() / pokemon.getMaxHealPoint().toFloat() },
                modifier = Modifier.weight(1f),
                color = colorResource(id = R.color.black)
            )
            Text(
                text = "HP: ${pokemon.getCurrentHP()}",
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
