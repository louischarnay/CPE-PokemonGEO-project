package fr.cpe.pokemon_geo.ui.screen.starter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.cpe.pokemon_geo.R

@Composable
fun ProfileCreation(welcomeViewModel: WelcomeViewModel) {
    var pseudo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(20.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokeball_blue),
            contentDescription = "Pokeball blue",
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = stringResource(id = R.string.welcome_title),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.enter_pseudo_label),
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = pseudo,
            onValueChange = { pseudo = it },
            label = { Text(text = stringResource(id = R.string.pseudo_label)) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            modifier = Modifier.height(50.dp).width(200.dp),
            onClick = { welcomeViewModel.createProfile(pseudo) }
        ) {
            Text(
                text = stringResource(id = R.string.continue_button),
                fontSize = 20.sp
            )
        }
    }

}