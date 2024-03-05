package fr.cpe.pokemon_geo.ui.screen.starter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.Pokemon
import fr.cpe.pokemon_geo.ui.component.PokemonCardImage
import fr.cpe.pokemon_geo.utils.findPokemonById

@Composable
fun Starter(pokemons: List<Pokemon>, starterViewModel: StarterViewModel = hiltViewModel()) {
    val grassStarter = findPokemonById(pokemons, 1)
    val fireStarter = findPokemonById(pokemons, 4)
    val waterStart = findPokemonById(pokemons, 7)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = stringResource(id = R.string.choose_starter_title))
        StarterButton(grassStarter, starterViewModel)
        StarterButton(fireStarter, starterViewModel)
        StarterButton(waterStart, starterViewModel)
    }
}

@Composable
fun StarterButton(pokemon: Pokemon, starterViewModel: StarterViewModel) {
    Column(
        modifier = Modifier.clickable {
            starterViewModel.chooseStarter(pokemon)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {

        PokemonCardImage(pokemon)
        Text(text = pokemon.getName())
    }
}