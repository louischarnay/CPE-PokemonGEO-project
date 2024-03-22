package fr.cpe.pokemon_geo.model.fight

import fr.cpe.pokemon_geo.model.pokemon.Pokemon

class Fight(
    private var myPokemon: Pokemon,
    private var opponentPokemon: Pokemon
) {
    private var turn = 0
    private var myPokemonCurrentHP = 100
    private var opponentPokemonCurrentHP = 100

    fun getMyPokemon(): Pokemon {
        return myPokemon
    }

    fun getOpponentPokemon(): Pokemon {
        return opponentPokemon
    }

    fun getMyPokemonCurrentHP(): Int {
        return myPokemonCurrentHP
    }

    fun getOpponentPokemonCurrentHP(): Int {
        return opponentPokemonCurrentHP
    }

    fun attack(): Int {
        val damage = (10..80).random()
        opponentPokemonCurrentHP -= damage
        return damage
    }

    fun opponentAttack(): Int {
        val damage = (10..50).random()
        myPokemonCurrentHP -= damage
        return damage
    }

    fun opponentEscape(): Boolean {
        if (turn < 2) return false
        return (0..4).random() == 0
    }

}