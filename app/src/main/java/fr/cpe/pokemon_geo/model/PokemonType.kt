package fr.cpe.pokemon_geo.model

import fr.cpe.pokemon_geo.R


enum class POKEMON_TYPE(val image: Int, val type: String) {
    ACIER(R.drawable.acier, "Acier"),
    COMBAT(R.drawable.combat, "Combat"),
    DRAGON(R.drawable.dragon, "Dragon"),
    EAU(R.drawable.eau, "Eau"),
    ELECTRIQUE(R.drawable.electrique, "Electrique"),
    FEE(R.drawable.fee, "Fee"),
    FEU(R.drawable.feu, "Feu"),
    GLACE(R.drawable.glace, "Glace"),
    INSECTE(R.drawable.insecte, "Insecte"),
    NORMAL(R.drawable.normal, "Normal"),
    PLANTE(R.drawable.plante, "Plante"),
    POISON(R.drawable.poison, "Poison"),
    PSY(R.drawable.psy, "Psy"),
    ROCHE(R.drawable.roche, "Roche"),
    SOL(R.drawable.sol, "Sol"),
    SPECTRE(R.drawable.spectre, "Spectre"),
    VOL(R.drawable.vol, "Vol")
}