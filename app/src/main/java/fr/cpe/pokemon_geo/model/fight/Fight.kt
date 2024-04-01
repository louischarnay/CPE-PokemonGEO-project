package fr.cpe.pokemon_geo.model.fight

import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats

class Fight(
    private var myPokemon: PokemonWithStats,
    private var opponentPokemon: PokemonWithStats
) {
    private var turn = 0
    private var hasEscaped = false

    fun getMyPokemon(): PokemonWithStats {
        return myPokemon
    }

    fun getOpponentPokemon(): PokemonWithStats {
        return opponentPokemon
    }

    fun attack() {
        val damage = (10..80).random()
        opponentPokemon.decreaseHP(damage)
        turn++
    }

    fun opponentAttack() {
        val damage = (10..50).random()
        myPokemon.decreaseHP(damage)
        turn++
    }

    fun opponentEscape(): Boolean {
        if (turn < 2) return false
        hasEscaped = (0..4).random() == 0
        return hasEscaped
    }

    fun isOver(): Boolean {
        return myPokemon.getCurrentHP() <= 0 || opponentPokemon.getCurrentHP() <= 0 || hasEscaped
    }

    fun getWinner(): PokemonWithStats? {
        if (myPokemon.getCurrentHP() <= 0) return opponentPokemon
        if (opponentPokemon.getCurrentHP() <= 0) return myPokemon
        return null
    }
}