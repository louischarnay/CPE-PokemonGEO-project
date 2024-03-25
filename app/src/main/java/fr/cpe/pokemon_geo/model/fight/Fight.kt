package fr.cpe.pokemon_geo.model.fight

class Fight(
    private var myPokemon: PokemonFighter,
    private var opponentPokemon: PokemonFighter
) {
    private var turn = 0
    private var hasEscaped = false

    fun getMyPokemon(): PokemonFighter {
        return myPokemon
    }

    fun getOpponentPokemon(): PokemonFighter {
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

    fun getWinner(): PokemonFighter? {
        if (myPokemon.getCurrentHP() <= 0) return opponentPokemon
        if (opponentPokemon.getCurrentHP() <= 0) return myPokemon
        return null
    }
}