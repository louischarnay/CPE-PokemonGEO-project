package fr.cpe.pokemon_geo.utils

import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats
import org.json.JSONArray
import java.io.InputStream

fun loadPokemonsFromResources(resources: InputStream): MutableList<Pokemon> {
    //start coroutine

    val jsonString = resources.bufferedReader().use { it.readText() }
    val jsonArray = JSONArray(jsonString)

    val pokemonList = mutableListOf<Pokemon>()
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val id = jsonObject.getInt("id")
        val name = jsonObject.getString("name")
        val image = jsonObject.getString("image")
        val type1 = jsonObject.getString("type1")
        val type2 = jsonObject.optString("type2", "")
        val pokemon =
            if (type2 == "") Pokemon(id, name, isUnknownPokemon = false, image, type1)
            else Pokemon(id, name, isUnknownPokemon = false, image, type1, type2)
        pokemonList.add(pokemon)
    }
    return pokemonList
}

fun buildPokemonWithStatsFromOrder(json: String, order: Int, id: Int, healPoint: Int, healPointLost: Int, attack: Int): PokemonWithStats {
    val jsonArray = JSONArray(json)

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        if (jsonObject.getInt("id") == order) {
            val name = jsonObject.getString("name")
            val image = jsonObject.getString("image")
            val type1 = jsonObject.getString("type1")
            val type2 = jsonObject.optString("type2", "")
            return PokemonWithStats(
                order = order,
                name = name,
                imageName = image,
                type1 = type1,
                type2 = if (type2 == "") null else type2,
                id = id,
                maxHealPoint = healPoint,
                healPointLoss = healPointLost,
                attack = attack
            )
        }
    }
    throw IllegalArgumentException("No pokemon with id $id")
}

fun findPokemonByOrder(pokemons: List<Pokemon>, order: Int): Pokemon {
    return pokemons.find { it.getOrder() == order } ?: throw IllegalArgumentException("No pokemon with id $order")
}
