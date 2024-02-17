package fr.cpe.pokemon_geo.model

import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.POKEMON_TYPE.*
import java.util.Locale

object SearchPokemonType {
    private val bugType = PokemonType(
        name = Insecte,
        frontResource = R.drawable.type_bug,
        superEffective = listOf(Ténèbres, Plante, Psy, Spectre, Vol),
        inefficient = listOf(Combat, Feu, Fee, Roche),
    )

    private val darkType = PokemonType(
        name = Ténèbres,
        frontResource = R.drawable.type_dark,
        superEffective = listOf(Spectre, Psy),
        inefficient = listOf(Combat, Insecte, Fee),
        ineffective = listOf(Ténèbres),
    )

    private val dragonType = PokemonType(
        name = Dragon,
        frontResource = R.drawable.type_dragon,
        superEffective = listOf(Dragon),
        inefficient = listOf(Acier, Fee, Glace),
    )

    private val electricType = PokemonType(
        name = Electrique,
        frontResource = R.drawable.type_electric,
        superEffective = listOf(Eau, Vol),
        inefficient = listOf(Dragon, Electrique, Plante),
        ineffective = listOf(Acier),
    )

    private val fairyType = PokemonType(
        name = Fee,
        frontResource = R.drawable.type_fairy,
        superEffective = listOf(Combat, Dragon, Ténèbres),
        inefficient = listOf(Acier, Feu, Poison),
        ineffective = listOf(Insecte),
    )

    private val fightingType = PokemonType(
        name = Combat,
        frontResource = R.drawable.type_fighting,
        superEffective = listOf(Glace, Normal, Roche, Ténèbres, Acier),
        inefficient = listOf(Insecte, Psy, Spectre, Vol),
        ineffective = listOf(Fee),
    )

    private val fireType = PokemonType(
        name = Feu,
        frontResource = R.drawable.type_fire,
        superEffective = listOf(Insecte, Plante, Glace, Acier),
        inefficient = listOf(Dragon, Feu, Roche, Eau),
    )

    private val flyingType = PokemonType(
        name = Vol,
        frontResource = R.drawable.type_flying,
        superEffective = listOf(Insecte, Combat, Plante),
        inefficient = listOf(Electrique, Roche, Acier),
        ineffective = listOf(Sol),
    )

    private val ghostType = PokemonType(
        name = Spectre,
        frontResource = R.drawable.type_ghost,
        superEffective = listOf(Spectre, Psy),
        inefficient = listOf(Acier, Ténèbres),
        ineffective = listOf(Normal),
    )

    private val grassType = PokemonType(
        name = Plante,
        frontResource = R.drawable.type_grass,
        superEffective = listOf(Eau, Sol, Roche),
        inefficient = listOf(Feu, Plante, Vol, Insecte, Poison),
    )

    private val groundType = PokemonType(
        name = Sol,
        frontResource = R.drawable.type_ground,
        superEffective = listOf(Electrique, Feu, Poison, Roche, Acier),
        inefficient = listOf(Insecte, Plante),
        ineffective = listOf(Vol),
    )

    private val iceType = PokemonType(
        name = Glace,
        frontResource = R.drawable.type_ice,
        superEffective = listOf(Dragon, Plante, Vol, Sol),
        inefficient = listOf(Glace),
    )

    private val normalType = PokemonType(
        name = Normal,
        frontResource = R.drawable.type_normal,
        superEffective = emptyList(),
        inefficient = listOf(Acier, Roche),
        ineffective = listOf(Spectre),
    )

    private val poisonType = PokemonType(
        name = Poison,
        frontResource = R.drawable.type_poison,
        superEffective = listOf(Fee, Plante),
        inefficient = listOf(Poison, Sol, Roche, Spectre),
        ineffective = listOf(Acier),
    )

    private val psychicType = PokemonType(
        name = Psy,
        frontResource = R.drawable.type_psychic,
        superEffective = listOf(Combat, Poison),
        inefficient = listOf(Acier, Psy),
        ineffective = listOf(Ténèbres),
    )

    private val rockType = PokemonType(
        name = Roche,
        frontResource = R.drawable.type_rock,
        superEffective = listOf(Insecte, Feu, Vol, Glace),
        inefficient = listOf(Combat, Sol, Acier),
    )

    private val steelType = PokemonType(
        name = Acier,
        frontResource = R.drawable.type_steel,
        superEffective = listOf(Fee, Glace, Roche),
        inefficient = listOf(Acier, Dragon, Feu),
    )

    private val waterType = PokemonType(
        name = Eau,
        frontResource = R.drawable.type_water,
        superEffective = listOf(Feu, Sol, Roche),
        inefficient = listOf(Dragon, Eau, Plante),
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
