package fr.cpe.pokemon_geo.ui.screen.starter

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import fr.cpe.pokemon_geo.model.pokemon.Pokemon

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Welcome(pokemons: List<Pokemon>, welcomeViewModel: WelcomeViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize().zIndex(10f)) { _ ->

        val showProfileScreen by welcomeViewModel.showProfileScreen.collectAsState()

        if (showProfileScreen) {
            ProfileCreation(welcomeViewModel)
        } else {
            StarterChoice(pokemons, welcomeViewModel)
        }
    }
}