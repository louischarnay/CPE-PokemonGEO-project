package fr.cpe.pokemon_geo.model.fight

import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats

class Fight(
    private var myPokemon: PokemonWithStats,
    private var opponentPokemon: PokemonWithStats
) {
    private var turn = 0
    private var hasEscaped = false

    fun getUserPokemon(): PokemonWithStats {
        return myPokemon
    }

    fun getOpponentPokemon(): PokemonWithStats {
        return opponentPokemon
    }

    fun attack() {
        val attack = myPokemon.getAttack()
        val damage = (attack * 5..attack * 15).random()
        opponentPokemon.decreaseHP(damage)
        turn++
    }

    fun opponentAttack() {
        val attack = opponentPokemon.getAttack()
        val damage = (attack * 5..attack * 15).random()
        myPokemon.decreaseHP(damage)
        turn++
    }

    fun tryToEscapeAsOpponent(): Boolean {
        if (turn < 2) return false
        hasEscaped = (0..4).random() == 0
        return hasEscaped
    }

    fun isOver(): Boolean {
        return myPokemon.getCurrentHP() <= 0 || opponentPokemon.getCurrentHP() <= 0 || hasEscaped
    }

    fun isUserWinner(): Boolean {
        return opponentPokemon.getCurrentHP() <= 0
    }
}