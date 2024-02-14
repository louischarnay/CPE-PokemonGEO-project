package fr.cpe.pokemon_geo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.cpe.pokemon_geo.ui.screen.pokedex.Pokedex
import fr.cpe.pokemon_geo.ui.screen.pokedex.PokedexViewModel
import fr.cpe.pokemon_geo.ui.screen.pokemon_card.PokemonCard
import fr.cpe.pokemon_geo.ui.theme.PokemongeoTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        setContent {
            PokemongeoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Pokedex()
                    //AppNavigation()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemongeoTheme {}
}