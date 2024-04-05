package fr.cpe.pokemon_geo.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.ui.navigation.Screen

@Composable
fun Profile(navController: NavController, profileViewModel: ProfileViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        profileViewModel.fetchProfile()
    }

    val profileState by profileViewModel.profile.collectAsState()

    profileState?.let { profile ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_account),
                contentDescription = "Profile icon",
                modifier = Modifier.size(150.dp)
            )
            Text(text = profile.pseudo, fontSize = 30.sp)
            val expText = String.format(stringResource(R.string.user_experience), profile.experience)
            Text(text = expText, fontSize = 20.sp)

            Spacer(modifier = Modifier.size(20.dp))

            Row {
                TextButton(onClick = { navController.navigate(Screen.UserInventory.route) }) {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.inventory_item),
                            contentDescription = "Inventory icon",
                            modifier = Modifier.size(75.dp)
                        )
                        Text(text = stringResource(R.string.inventory))

                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                TextButton(onClick = { navController.navigate(Screen.UserPokemon.route) }) {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.inventory_pokemon),
                            contentDescription = "Pokemon icon",
                            modifier = Modifier.size(75.dp)
                        )
                        Text(text = stringResource(R.string.pokemon))
                    }
                }
            }
        }
    }
}