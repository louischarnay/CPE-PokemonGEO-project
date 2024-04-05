package fr.cpe.pokemon_geo.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.pokemon.Pokemon

@Composable
fun PokemonDetails(pokemon: Pokemon, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        confirmButton = {
            Button(onClick = onClose) {
                Text(text = stringResource(R.string.close))
            }
        },
        text = { PokemonCard(pokemon) }
    )
}