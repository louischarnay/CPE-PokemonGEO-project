package fr.cpe.pokemon_geo.model.pokemon

import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.pokemon.POKEMON_TYPE.*
import java.util.Locale

object SearchPokemonType {
    private val bugType = PokemonType(
        name = Bug,
        frontResource = R.drawable.type_bug,
        superEffective = listOf(Dark, Grass, Psychic, Ghost, Flying),
        inefficient = listOf(Fighting, Fire, Fairy, Rock),
    )

    private val darkType = PokemonType(
        name = Dark,
        frontResource = R.drawable.type_dark,
        superEffective = listOf(Ghost, Psychic),
        inefficient = listOf(Fighting, Bug, Fairy),
        ineffective = listOf(Dark),
    )

    private val dragonType = PokemonType(
        name = Dragon,
        frontResource = R.drawable.type_dragon,
        superEffective = listOf(Dragon),
        inefficient = listOf(Steel, Fairy, Ice),
    )

    private val electricType = PokemonType(
        name = Electric,
        frontResource = R.drawable.type_electric,
        superEffective = listOf(Water, Flying),
        inefficient = listOf(Dragon, Electric, Grass),
        ineffective = listOf(Steel),
    )

    private val fairyType = PokemonType(
        name = Fairy,
        frontResource = R.drawable.type_fairy,
        superEffective = listOf(Fighting, Dragon, Dark),
        inefficient = listOf(Steel, Fire, Poison),
        ineffective = listOf(Bug),
    )

    private val fightingType = PokemonType(
        name = Fighting,
        frontResource = R.drawable.type_fighting,
        superEffective = listOf(Ice, Normal, Rock, Dark, Steel),
        inefficient = listOf(Bug, Psychic, Ghost, Flying),
        ineffective = listOf(Fairy),
    )

    private val fireType = PokemonType(
        name = Fire,
        frontResource = R.drawable.type_fire,
        superEffective = listOf(Bug, Grass, Ice, Steel),
        inefficient = listOf(Dragon, Fire, Rock, Water),
    )

    private val flyingType = PokemonType(
        name = Flying,
        frontResource = R.drawable.type_flying,
        superEffective = listOf(Bug, Fighting, Grass),
        inefficient = listOf(Electric, Rock, Steel),
        ineffective = listOf(Ground),
    )

    private val ghostType = PokemonType(
        name = Ghost,
        frontResource = R.drawable.type_ghost,
        superEffective = listOf(Ghost, Psychic),
        inefficient = listOf(Steel, Dark),
        ineffective = listOf(Normal),
    )

    private val grassType = PokemonType(
        name = Grass,
        frontResource = R.drawable.type_grass,
        superEffective = listOf(Water, Ground, Rock),
        inefficient = listOf(Fire, Grass, Flying, Bug, Poison),
    )

    private val groundType = PokemonType(
        name = Ground,
        frontResource = R.drawable.type_ground,
        superEffective = listOf(Electric, Fire, Poison, Rock, Steel),
        inefficient = listOf(Bug, Grass),
        ineffective = listOf(Flying),
    )

    private val iceType = PokemonType(
        name = Ice,
        frontResource = R.drawable.type_ice,
        superEffective = listOf(Dragon, Grass, Flying, Ground),
        inefficient = listOf(Ice),
    )

    private val normalType = PokemonType(
        name = Normal,
        frontResource = R.drawable.type_normal,
        superEffective = emptyList(),
        inefficient = listOf(Steel, Rock),
        ineffective = listOf(Ghost),
    )

    private val poisonType = PokemonType(
        name = Poison,
        frontResource = R.drawable.type_poison,
        superEffective = listOf(Fairy, Grass),
        inefficient = listOf(Poison, Ground, Rock, Ghost),
        ineffective = listOf(Steel),
    )

    private val psychicType = PokemonType(
        name = Psychic,
        frontResource = R.drawable.type_psychic,
        superEffective = listOf(Fighting, Poison),
        inefficient = listOf(Steel, Psychic),
        ineffective = listOf(Dark),
    )

    private val rockType = PokemonType(
        name = Rock,
        frontResource = R.drawable.type_rock,
        superEffective = listOf(Bug, Fire, Flying, Ice),
        inefficient = listOf(Fighting, Ground, Steel),
    )

    private val steelType = PokemonType(
        name = Steel,
        frontResource = R.drawable.type_steel,
        superEffective = listOf(Fairy, Ice, Rock),
        inefficient = listOf(Steel, Dragon, Fire),
    )

    private val waterType = PokemonType(
        name = Water,
        frontResource = R.drawable.type_water,
        superEffective = listOf(Fire, Ground, Rock),
        inefficient = listOf(Dragon, Water, Grass),
    )

    fun byName(name: String): PokemonType {
        return when (name.lowercase(Locale.ROOT)) {
            "insecte" -> bugType
            "ténèbres" -> darkType
            "dragon" -> dragonType
            "electrique" -> electricType
            "fee" -> fairyType
            "combat" -> fightingType
            "feu" -> fireType
            "vol" -> flyingType
            "spectre" -> ghostType
            "plante" -> grassType
            "sol" -> groundType
            "glace" -> iceType
            "normal" -> normalType
            "poison" -> poisonType
            "psy" -> psychicType
            "roche" -> rockType
            "acier" -> steelType
            "eau" -> waterType
            else -> throw IllegalArgumentException("Unknown Pokemon type: $name")
        }
    }
}
