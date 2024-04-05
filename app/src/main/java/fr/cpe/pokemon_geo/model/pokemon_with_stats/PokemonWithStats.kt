package fr.cpe.pokemon_geo.model.pokemon_with_stats

import fr.cpe.pokemon_geo.model.pokemon.Pokemon

class PokemonWithStats(
    order : Int,
    name : String,
    imageName : String,
    type1 : String,
    type2 : String?,
    private val id : Int,
    private val maxHealPoint : Int,
    private var healPointLoss : Int = 0,
    private val attack : Int
) : Pokemon(
    order = order,
    name = name,
    isUnknownPokemon = false,
    imageName = imageName,
    type1 = type1,
    type2 = type2
) {

    fun getId(): Int {
        return id
    }

    fun getMaxHP(): Int {
        return maxHealPoint
    }

    fun getHPLoss(): Int {
        return healPointLoss
    }

    fun getAttack(): Int {
        return attack
    }

    fun getCurrentHP(): Int {
        return maxHealPoint - healPointLoss
    }

    fun decreaseHP(amount: Int) {
        healPointLoss += amount
        if (healPointLoss > maxHealPoint) {
            healPointLoss = maxHealPoint
        }
    }
}