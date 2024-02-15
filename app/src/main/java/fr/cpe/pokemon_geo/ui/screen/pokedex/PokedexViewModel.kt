package fr.cpe.pokemon_geo.ui.screen.pokedex

import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.model.Pokemon
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val application: Application
): ViewModel() {

    val pokemons = mutableListOf(
        Pokemon(1, "Bulbizarre", "p1", "Grass", "Poison"),
        Pokemon(2, "Herbizarre", "p2", "Grass", "Poison"),
        Pokemon(3, "Florizarre", "p3", "Grass", "Poison"),
        Pokemon(4, "Salamèche", "p4", "Fire"),
        Pokemon(5, "Reptincel", "p5", "Fire"),
        Pokemon(6, "Dracaufeu", "p6", "Fire", "Flying"),
        Pokemon(7, "Carapuce", "p7", "Water"),
        Pokemon(8, "Carabaffe", "p8", "Water"),
        Pokemon(9, "Tortank", "p9", "Water"),
        Pokemon(10, "Chenipan", "p10", "Bug"),
        Pokemon(11, "Chrysacier", "p11", "Bug"),
        Pokemon(12, "Papilusion", "p12", "Bug", "Flying"),
        Pokemon(13, "Aspicot", "p13", "Bug", "Poison"),
        Pokemon(14, "Coconfort", "p14", "Bug", "Poison"),
        Pokemon(15, "Dardargnan", "p15", "Bug", "Poison"),
    )
}