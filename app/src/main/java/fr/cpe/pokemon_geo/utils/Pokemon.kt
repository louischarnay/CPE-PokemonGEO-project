package fr.cpe.pokemon_geo.utils

import fr.cpe.pokemon_geo.model.Pokemon
import org.json.JSONArray
import java.io.InputStream

fun loadPokemonsFromResources(resources: InputStream): MutableList<Pokemon> {
    val jsonString = resources.bufferedReader().use { it.readText() }
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

fun loadPokemonFromId(resources: InputStream, id: Int): Pokemon {
    val jsonString = resources.bufferedReader().use { it.readText() }
    val jsonArray = JSONArray(jsonString)

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        if (jsonObject.getInt("id") == id) {
            val name = jsonObject.getString("name")
            val image = jsonObject.getString("image")
            val type1 = jsonObject.getString("type1")
            val type2 = jsonObject.optString("type2", null)
            return Pokemon(id, name, image, type1, type2)
        }
    }
    throw IllegalArgumentException("No pokemon with id $id")
}