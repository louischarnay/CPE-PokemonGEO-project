package fr.cpe.pokemon_geo.model.pokemon

import fr.cpe.pokemon_geo.R

open class Pokemon(
    private var order: Int,
    private var name: String,
    private var isUserPokemon: Boolean,
    imageName: String,
    type1: String,
    type2: String? = null,
    healPoint: Int? = 0,
    healPointLoss: Int? = 0,
    attack: Int? = 0,
) {
    private var frontResource = 0
    private var type1: PokemonType
    private var type2: PokemonType? = null
    private var healPoint: Int = 0
    private var healPointLoss: Int = 0
    private var attack: Int = 0

    init {
        this.frontResource = R.drawable::class.java.getField(imageName).getInt(null)
        this.type1 = SearchPokemonType.byName(type1)
        if (type2 != null) this.type2 = SearchPokemonType.byName(type2)
        if (healPoint != null) this.healPoint = healPoint
        if (healPointLoss != null) this.healPointLoss = healPointLoss
        if (attack != null) this.attack = attack
    }

    fun getOrder(): Int {
        return order
    }

    fun getName(): String {
        return name
    }

    fun isUserPokemon(): Boolean {
        return isUserPokemon
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

    fun getHealPoint(): Int {
        return healPoint
    }

    fun getHealPointLoss(): Int {
        return healPointLoss
    }

    fun getAttack(): Int {
        return attack
    }
}