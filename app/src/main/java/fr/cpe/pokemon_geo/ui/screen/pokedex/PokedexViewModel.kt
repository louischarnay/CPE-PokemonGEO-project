package fr.cpe.pokemon_geo.ui.screen.pokedex

import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.Pokemon
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val application: Application
): ViewModel() {

    val pokemons: MutableList<Pokemon> by lazy {
        loadPokemonsFromResources()
    }

    private fun loadPokemonsFromResources(): MutableList<Pokemon> {
        val inputStream = application.resources.openRawResource(R.raw.pokemon)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)

        val pokemonList = mutableListOf<Pokemon>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getInt("id")
            val name = jsonObject.getString("name")
            val image = jsonObject.getString("image")
            val type1 = jsonObject.getString("type1")
            val type2 = jsonObject.optString("type2", null)
            val pokemon = Pokemon(id, name, image, type1, type2)
            pokemonList.add(pokemon)
        }
        return pokemonList
    }
}