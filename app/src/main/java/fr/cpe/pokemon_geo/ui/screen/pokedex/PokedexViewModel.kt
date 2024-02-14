package fr.cpe.pokemon_geo.ui.screen.pokedex

import androidx.lifecycle.ViewModel
import fr.cpe.pokemon_geo.model.POKEMON_TYPE
import fr.cpe.pokemon_geo.model.Pokemon

class PokedexViewModel: ViewModel() {

    val pokemons = mutableListOf(
        Pokemon(1, "Bulbizarre", 0, POKEMON_TYPE.Plante, POKEMON_TYPE.Poison),
        Pokemon(2, "Herbizarre", 0, POKEMON_TYPE.Plante, POKEMON_TYPE.Poison),
        Pokemon(3, "Florizarre", 0, POKEMON_TYPE.Plante, POKEMON_TYPE.Poison),
        Pokemon(4, "Salamèche", 0, POKEMON_TYPE.Feu),
        Pokemon(5, "Reptincel", 0, POKEMON_TYPE.Feu),
        Pokemon(6, "Dracaufeu", 0, POKEMON_TYPE.Feu, POKEMON_TYPE.Vol),
        Pokemon(7, "Carapuce", 0, POKEMON_TYPE.Eau),
        Pokemon(8, "Carabaffe", 0, POKEMON_TYPE.Eau),
        Pokemon(9, "Tortank", 0, POKEMON_TYPE.Eau),
        Pokemon(10, "Chenipan", 0, POKEMON_TYPE.Insecte),
        Pokemon(11, "Chrysacier", 0, POKEMON_TYPE.Insecte),
        Pokemon(12, "Papilusion", 0, POKEMON_TYPE.Insecte, POKEMON_TYPE.Vol),
        Pokemon(13, "Aspicot", 0, POKEMON_TYPE.Insecte, POKEMON_TYPE.Poison),
        Pokemon(14, "Coconfort", 0, POKEMON_TYPE.Insecte, POKEMON_TYPE.Poison),
        Pokemon(15, "Dardargnan", 0, POKEMON_TYPE.Insecte, POKEMON_TYPE.Poison),
    )
}