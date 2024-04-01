package fr.cpe.pokemon_geo.utils

import fr.cpe.pokemon_geo.model.pokemon.Pokemon
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
        val pokemon = Pokemon(id, name, false, image, type1, type2)
        pokemonList.add(pokemon)
    }
    return pokemonList
}

fun loadPokemonFromId(resources: InputStream, id: Int, healPoint: Int, healPointLost: Int, attack: Int ): Pokemon {
    val jsonString = resources.bufferedReader().use { it.readText() }
    val jsonArray = JSONArray(jsonString)

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        if (jsonObject.getInt("id") == id) {
            val name = jsonObject.getString("name")
            val image = jsonObject.getString("image")
            val type1 = jsonObject.getString("type1")
            val type2 = jsonObject.optString("type2", null)
            return Pokemon(id, name, true, image, type1, type2, healPoint, healPointLost, attack)
        }
    }
    throw IllegalArgumentException("No pokemon with id $id")
}

fun findPokemonById(pokemons: List<Pokemon>, id: Int): Pokemon {
    return pokemons.find { it.getOrder() == id } ?: throw IllegalArgumentException("No pokemon with id $id")
}
