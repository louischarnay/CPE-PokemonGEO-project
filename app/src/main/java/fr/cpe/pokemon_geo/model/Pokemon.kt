package fr.cpe.pokemon_geo.model

class Pokemon(
    var order: Int,
    var name: String,
    var frontResource: Int,
    private var type1: POKEMON_TYPE,
    private var type2: POKEMON_TYPE? = null,
    var height: Int = 0,
    var weight: Int = 0
) {
    fun getType1(): POKEMON_TYPE {
        return type1
    }

    fun getType2(): POKEMON_TYPE? {
        return type2
    }
}