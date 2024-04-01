package fr.cpe.pokemon_geo.model.pokemon_with_stats

import fr.cpe.pokemon_geo.model.pokemon.Pokemon

class PokemonWithStats(
    order : Int,
    name : String,
    imageName : String,
    type1 : String,
    type2 : String?,
    private val healPoint : Int,
    private var healPointLoss : Int?,
    private val attack : Int
) : Pokemon(
    order = order,
    name = name,
    isUnknownPokemon = false,
    imageName = imageName,
    type1 = type1,
    type2 = type2
) {

    fun getHealPoint(): Int {
        return healPoint
    }

    fun getHealPointLoss(): Int {
        return healPointLoss ?: 0
    }

    fun getAttack(): Int {
        return attack
    }

    fun getCurrentHP(): Int {
        return healPoint - (healPointLoss ?: 0)
    }

    fun decreaseHP(amount: Int) {
        healPointLoss.let {
            healPointLoss = healPointLoss?.plus(amount)
        }
    }
}