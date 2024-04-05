package fr.cpe.pokemon_geo.model.pokemon

import fr.cpe.pokemon_geo.R

open class Pokemon(
    private var order: Int,
    private var name: String,
    private var isUnknownPokemon: Boolean = false,
    imageName: String,
    type1: String,
    type2: String? = null,
) {
    private var frontResource = 0
    private var type1: PokemonType
    private var type2: PokemonType? = null

    init {
        this.frontResource = R.drawable::class.java.getField(imageName).getInt(null)
        this.type1 = SearchPokemonType.byName(type1)
        if (type2 != null) this.type2 = SearchPokemonType.byName(type2)
    }

    fun getOrder(): Int {
        return order
    }

    fun getName(): String {
        return name
    }

    fun isUnknownPokemon(): Boolean {
        return isUnknownPokemon
    }

    fun setUnknownPokemon() {
        isUnknownPokemon = true
    }

    fun getFrontResource(): Int {
        return frontResource
    }

    fun getType1(): PokemonType {
        return type1
    }

    fun getType2(): PokemonType? {
        return type2
    }

    fun getAttackMultiplier(opponent: Pokemon): Double {
        return type1.attackMultiplier(opponent.getType1()) * (type2?.attackMultiplier(opponent.getType1()) ?: 1.0)
    }
}