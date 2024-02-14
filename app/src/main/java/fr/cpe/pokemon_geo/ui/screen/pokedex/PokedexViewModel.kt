package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.lifecycle.ViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.POKEMON_TYPE
import fr.cpe.pokemon_geo.model.Pokemon
import org.json.JSONArray

class PokedexViewModel: ViewModel() {
    fun getPokemons(): List<Pokemon> {
        //get resources
        val res = resources.openRawResource(R.raw.pokemons)
        val pokemonsJson = JSONArray(res.bufferedReader().use { it.readText() })
        val pokemons = mutableListOf<Pokemon>()

        for (i in 0 until pokemonsJson.length()) {
            val json = pokemonsJson.getJSONObject(i)
            val id = json.getInt("id")
            val name = json.getString("name")
            val image = json.getInt("image")
            val type1 = enumValueOf<POKEMON_TYPE>(json.getString("type1"))
            val type2 =
                if (json.has("type2")) enumValueOf<POKEMON_TYPE>(json.getString("type2")) else null

            val pokemon = Pokemon(id, name, image, type1, type2)
            pokemons.add(pokemon)
        }

        return pokemons
    }
}