package fr.cpe.pokemon_geo.ui.screen.fight

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats

@Composable
fun Fight(
    navController: NavController,
    userPokemonId: Int,
    opponentPokemonId: Int,
    pokeBallName: String?,
    fightViewModel: FightViewModel = hiltViewModel()
) {
    if (!fightViewModel.hasTriedCapture.value && pokeBallName != null) {
        fightViewModel.capture(pokeBallName, navController)
    }

    LaunchedEffect(Unit) {
        fightViewModel.initFight(userPokemonId, opponentPokemonId)
    }

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
            // Top pokemon
            fightViewModel.fight.value?.getOpponentPokemon()?.let {
                PokemonFighterStatus(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(y = (-120).dp),
                    pokemon = it
                )
                Image(
                    painter = painterResource(id = it.getFrontResource()),
                    contentDescription = "Opponent pokemon",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .offset(y = (-100).dp, x = 110.dp)
                )
            }

            // Bottom pokemon
            fightViewModel.fight.value?.getUserPokemon()?.let {
                PokemonFighterStatus(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(y = 100.dp),
                    pokemon = it
                )
                Image(
                    painter = painterResource(id = it.getFrontResource()),
                    contentDescription = "User pokemon",
                    modifier = Modifier
                        .size(140.dp)
                        .align(Alignment.Center)
                        .offset(y = 120.dp, x = (-100).dp)
                )
            }
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
                onClick = { fightViewModel.attack(navController) }
            ) {
                Text(
                    text = stringResource(R.string.attack),
                    fontSize = 40.sp
                )
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
                    text = stringResource(R.string.capture),
                    onClick = { fightViewModel.showInventory(navController) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                FightSecondaryButton(
                    text = stringResource(R.string.escape),
                    onClick = { fightViewModel.end(navController) },
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

@Composable
fun PokemonFighterStatus(
    modifier: Modifier = Modifier,
    pokemon: PokemonWithStats,
) {
    val hpProgress = pokemon.getCurrentHP() / pokemon.getMaxHP().toFloat()

    Column(
        modifier = modifier
            .background(colorResource(id = R.color.background))
            .width(160.dp)
    ) {
        Text(text = pokemon.getName(), fontSize = 16.sp, modifier = Modifier.padding(horizontal = 5.dp))
        LinearProgressIndicator(
            progress = { hpProgress },
            modifier = Modifier
                .height(10.dp)
                .padding(horizontal = 5.dp, vertical = 2.dp),
            color = colorResource(id = R.color.red),
        )
    }
}
