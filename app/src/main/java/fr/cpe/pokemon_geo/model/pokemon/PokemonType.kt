package fr.cpe.pokemon_geo.model.pokemon

class PokemonType(
    private var name: POKEMON_TYPE,
    private var frontResource: Int,
    private var superEffective: List<POKEMON_TYPE>,
    private var inefficient: List<POKEMON_TYPE>,
    private var ineffective: List<POKEMON_TYPE> = emptyList()
) {
    fun getName(): String {
        return name.toString().lowercase().replaceFirstChar { it.uppercase() }
    }

    fun getFrontResource(): Int {
        return frontResource
    }
}
