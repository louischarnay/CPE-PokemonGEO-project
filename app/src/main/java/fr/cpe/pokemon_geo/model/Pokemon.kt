package fr.cpe.pokemon_geo.model

import fr.cpe.pokemon_geo.R

class Pokemon(
    private var order: Int,
    private var name: String,
    imageName: String,
    type1: String,
    type2: String? = null
) {
    private var frontResource = 0
    private var type1: PokemonType
    private var type2: PokemonType? = null
    private var height = 0
    private var weight = 0

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

    fun getFrontResource(): Int {
        return frontResource
    }

    fun getType1(): PokemonType {
        return type1
    }

    fun getType2(): PokemonType? {
        return type2
    }
}