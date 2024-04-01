package fr.cpe.pokemon_geo.ui.screen.fight

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats

@Composable
fun Fight(
    userPokemonId: Int,
    opponentPokemonId: Int,
    fightViewModel: FightViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        fightViewModel.initFight(0, opponentPokemonId)
    }

    val pokemon = PokemonWithStats(
        0,
        "Pikachu",
        "pikachu",
        "Electric",
        null,
        100,
        0,
        10
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
        ) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.fight_background),
                contentDescription = "Fight background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Top right image
            Image(
                painter = painterResource(id = pokemon.getFrontResource()),
                contentDescription = "Opponent pokemon",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
                    .offset(y = (-100).dp, x = 110.dp)
            )
            // Bottom left image
            Image(
                painter = painterResource(id = pokemon.getFrontResource()),
                contentDescription = "User pokemon",
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.Center)
                    .offset(y = (120).dp, x = (-100).dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(12.dp),
        ) {
            // Fight button
            ElevatedButton(
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                onClick = { fightViewModel.attack() }
            ) {
                Text(text = "Attaque", fontSize = 40.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Secondary buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FightSecondaryButton(
                    text = "Pokémons",
                    onClick = { fightViewModel.showUserPokemons() },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                FightSecondaryButton(
                    text = "Capture",
                    onClick = { fightViewModel.showInventory() },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                FightSecondaryButton(
                    text = "Fuite",
                    onClick = { fightViewModel.escape() },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun FightSecondaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    ElevatedButton(
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        modifier = modifier.fillMaxHeight()
    ) {
        Text(text = text, fontSize = 15.sp)
    }
}
