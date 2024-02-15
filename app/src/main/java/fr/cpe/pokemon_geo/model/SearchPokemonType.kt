package fr.cpe.pokemon_geo.model

import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.POKEMON_TYPE.*
import java.util.Locale

object SearchPokemonType {
    private val bugType = PokemonType(
        name = Bug,
        frontResource = R.drawable.type_bug,
        superEffective = listOf(Dark, Grass, Psychic),
        inefficient = listOf(Fairy, Fire, Fighting, Flying, Ghost, Poison, Steel),
    )

    private val darkType = PokemonType(
        name = Dark,
        frontResource = R.drawable.type_dark,
        superEffective = listOf(Ghost, Psychic),
        inefficient = listOf(Dark, Fairy, Fighting),
    )

    private val dragonType = PokemonType(
        name = Dragon,
        frontResource = R.drawable.type_dragon,
        superEffective = listOf(Dragon),
        inefficient = listOf(Steel),
        ineffective = listOf(Fairy),
    )

    private val electricType = PokemonType(
        name = Electric,
        frontResource = R.drawable.type_electric,
        superEffective = listOf(Flying, Water),
        inefficient = listOf(Dragon, Electric, Grass),
        ineffective = listOf(Ground),
    )

    private val fairyType = PokemonType(
        name = Fairy,
        frontResource = R.drawable.type_fairy,
        superEffective = listOf(Dark, Dragon, Fighting),
        inefficient = listOf(Fire, Poison, Steel),
    )

    private val fightingType = PokemonType(
        name = Fighting,
        frontResource = R.drawable.type_fighting,
        superEffective = listOf(Dark, Ice, Normal, Rock, Steel),
        inefficient = listOf(Bug, Fairy, Flying, Poison, Psychic),
        ineffective = listOf(Ghost),
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
    )

    private val ghostType = PokemonType(
        name = Ghost,
        frontResource = R.drawable.type_ghost,
        superEffective = listOf(Ghost, Psychic),
        inefficient = listOf(Dark),
        ineffective = listOf(Normal),
    )

    private val grassType = PokemonType(
        name = Grass,
        frontResource = R.drawable.type_grass,
        superEffective = listOf(Ground, Rock, Water),
        inefficient = listOf(Bug, Dragon, Fire, Flying, Grass, Poison, Steel),
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
        superEffective = listOf(Dragon, Flying, Grass, Ground),
        inefficient = listOf(Fire, Ice, Steel, Water),
    )

    private val normalType = PokemonType(
        name = Normal,
        frontResource = R.drawable.type_normal,
        superEffective = emptyList(),
        inefficient = listOf(Rock, Steel),
        ineffective = listOf(Ghost),
    )

    private val poisonType = PokemonType(
        name = Poison,
        frontResource = R.drawable.type_poison,
        superEffective = listOf(Fairy, Grass),
        inefficient = listOf(Ghost, Ground, Poison, Rock),
        ineffective = listOf(Steel),
    )

    private val psychicType = PokemonType(
        name = Psychic,
        frontResource = R.drawable.type_psychic,
        superEffective = listOf(Fighting, Poison),
        inefficient = listOf(Psychic, Steel),
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
        inefficient = listOf(Electric, Fire, Steel, Water),
    )

    private val waterType = PokemonType(
        name = Water,
        frontResource = R.drawable.type_water,
        superEffective = listOf(Fire, Ground, Rock),
        inefficient = listOf(Dragon, Grass, Water),
    )

    fun byName(name: String): PokemonType {
        return when (name.lowercase(Locale.ROOT)) {
            "bug" -> bugType
            "dark" -> darkType
            "dragon" -> dragonType
            "electric" -> electricType
            "fairy" -> fairyType
            "fighting" -> fightingType
            "fire" -> fireType
            "flying" -> flyingType
            "ghost" -> ghostType
            "grass" -> grassType
            "ground" -> groundType
            "ice" -> iceType
            "normal" -> normalType
            "poison" -> poisonType
            "psychic" -> psychicType
            "rock" -> rockType
            "steel" -> steelType
            "water" -> waterType
            else -> throw IllegalArgumentException("Unknown Pokemon type: $name")
        }
    }
}
