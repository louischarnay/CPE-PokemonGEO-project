package fr.cpe.pokemon_geo.ui.screen.starter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.utils.findPokemonById

@Composable
fun StarterChoice(pokemons: List<Pokemon>, welcomeViewModel: WelcomeViewModel) {
    val grassStarter = findPokemonById(pokemons, 1)
    val fireStarter = findPokemonById(pokemons, 4)
    val waterStart = findPokemonById(pokemons, 7)

    Column(
        modifier = Modifier.padding(15.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        Text(
            text = stringResource(id = R.string.choose_starter_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        StarterButton(grassStarter, welcomeViewModel)
        StarterButton(fireStarter, welcomeViewModel)
        StarterButton(waterStart, welcomeViewModel)
    }
}

@Composable
fun StarterButton(pokemon: Pokemon, welcomeViewModel: WelcomeViewModel) {
    Column(
        modifier = Modifier.clickable {
            welcomeViewModel.chooseStarter(pokemon)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {

        Image(
            painter = painterResource(id = pokemon.getFrontResource()),
            contentDescription = "Pokemon starter image",
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = pokemon.getName(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}