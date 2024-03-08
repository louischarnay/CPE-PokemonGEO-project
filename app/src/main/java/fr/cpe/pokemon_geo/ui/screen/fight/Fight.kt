package fr.cpe.pokemon_geo.ui.screen.fight

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.pokemon.Pokemon

@Composable
fun Fight(fightViewModel: FightViewModel = hiltViewModel()) {
    val pokemon = Pokemon(
        order = 1,
        name = "Dorian",
        imageName = "p1",
        type1 = "Normal",
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fight_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(12.dp),
        ) {
            ElevatedButton(
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Attaque", fontSize = 40.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FightSecondaryButton(
                    text = "Pokémons",
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                FightSecondaryButton(
                    text = "Capture",
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                FightSecondaryButton(
                    text = "Fuite",
                    onClick = { /*TODO*/ },
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