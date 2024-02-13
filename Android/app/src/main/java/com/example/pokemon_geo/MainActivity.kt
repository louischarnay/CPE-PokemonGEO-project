package com.example.pokemon_geo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokemon_geo.model.POKEMON_TYPE
import com.example.pokemon_geo.model.Pokemon
import com.example.pokemon_geo.ui.screen.Pokedex
import com.example.pokemon_geo.ui.theme.PokemongeoTheme
import org.json.JSONArray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val res = resources.openRawResource(R.raw.pokemons)
        val pokemonsJson = JSONArray(res.bufferedReader().use { it.readText() })
        val pokemons = mutableListOf<Pokemon>()

        for (i in 0 until pokemonsJson.length()) {
            val json = pokemonsJson.getJSONObject(i)
            val id = json.getInt("id")
            val name = json.getString("name")
            val image = json.getInt("image")
            val type1= enumValueOf<POKEMON_TYPE>(json.getString("type1"))
            val type2 = if (json.has("type2")) enumValueOf<POKEMON_TYPE>(json.getString("type2")) else null

            val pokemon = Pokemon(id, name, image, type1, type2)
            pokemons.add(pokemon)
        }

        setContent {
            PokemongeoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Pokedex(pokemons)
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