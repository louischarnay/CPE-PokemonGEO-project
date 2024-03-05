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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.ui.component.PokemonCardImage
import fr.cpe.pokemon_geo.ui.navigation.Routes
import fr.cpe.pokemon_geo.utils.findPokemonById

@Composable
fun Starter(pokemons: List<Pokemon>, navController: NavController, starterViewModel: StarterViewModel = hiltViewModel()) {
    val grassStarter = findPokemonById(pokemons, 1)
    val fireStarter = findPokemonById(pokemons, 4)
    val waterStart = findPokemonById(pokemons, 7)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        Text(
            text = stringResource(id = R.string.choose_starter_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        StarterButton(grassStarter, starterViewModel, navController)
        StarterButton(fireStarter, starterViewModel, navController)
        StarterButton(waterStart, starterViewModel, navController)
    }
}

@Composable
fun StarterButton(pokemon: Pokemon, starterViewModel: StarterViewModel, navController: NavController) {
    Column(
        modifier = Modifier.clickable {
            starterViewModel.chooseStarter(pokemon)
            navController.navigate(Routes.PROFILE)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {

        Image(
            painter = painterResource(id = pokemon.getFrontResource()),
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )
        Text(
            text = pokemon.getName(),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}