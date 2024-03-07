package fr.cpe.pokemon_geo.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.cpe.pokemon_geo.R

@Composable
fun Profile(navController: NavController, profileViewModel: ProfileViewModel = hiltViewModel()) {
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
                Text(text = "${profile.experience} XP", fontSize = 20.sp)
    
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Inventaire")
                }
                Button(onClick = { navController.navigate("user_pokemon") }) {
                    Text(text = "Pokémons")
                }
            }
        }
    }
}