package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.lifecycle.ViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.model.POKEMON_TYPE
import fr.cpe.pokemon_geo.model.Pokemon

class PokedexViewModel: ViewModel() {

    val pokemons = mutableListOf(
        Pokemon(1, "Bulbizarre", R.drawable.p1, POKEMON_TYPE.PLANTE, POKEMON_TYPE.POISON),
        Pokemon(2, "Herbizarre", R.drawable.p2, POKEMON_TYPE.PLANTE, POKEMON_TYPE.POISON),
        Pokemon(3, "Florizarre", R.drawable.p3, POKEMON_TYPE.PLANTE, POKEMON_TYPE.POISON),
        Pokemon(4, "Salamèche", R.drawable.p4, POKEMON_TYPE.FEU),
        Pokemon(5, "Reptincel", R.drawable.p5, POKEMON_TYPE.FEU),
        Pokemon(6, "Dracaufeu", R.drawable.p6, POKEMON_TYPE.FEU, POKEMON_TYPE.VOL),
        Pokemon(7, "Carapuce", R.drawable.p7, POKEMON_TYPE.EAU),
        Pokemon(8, "Carabaffe", R.drawable.p8, POKEMON_TYPE.EAU),
        Pokemon(9, "Tortank", R.drawable.p9, POKEMON_TYPE.EAU),
        Pokemon(10, "Chenipan", R.drawable.p10, POKEMON_TYPE.INSECTE),
        Pokemon(11, "Chrysacier", R.drawable.p11, POKEMON_TYPE.INSECTE),
        Pokemon(12, "Papilusion", R.drawable.p12, POKEMON_TYPE.INSECTE, POKEMON_TYPE.VOL),
        Pokemon(13, "Aspicot", R.drawable.p13, POKEMON_TYPE.INSECTE, POKEMON_TYPE.POISON),
        Pokemon(14, "Coconfort", R.drawable.p14, POKEMON_TYPE.INSECTE, POKEMON_TYPE.POISON),
        Pokemon(15, "Dardargnan", R.drawable.p15, POKEMON_TYPE.INSECTE, POKEMON_TYPE.POISON),
    )
}