package fr.cpe.pokemon_geo.model.fight

import fr.cpe.pokemon_geo.model.inventory_item.INVENTORY_ITEM
import fr.cpe.pokemon_geo.model.inventory_item.InventoryItemType
import fr.cpe.pokemon_geo.model.pokemon_with_stats.PokemonWithStats

class Fight(
    private var userPokemon: PokemonWithStats,
    private var opponentPokemon: PokemonWithStats
) {
    private var turn = 0
    private var hasEscaped = false

    fun getUserPokemon(): PokemonWithStats {
        return userPokemon
    }

    fun getOpponentPokemon(): PokemonWithStats {
        return opponentPokemon
    }

    fun hasOpponentEscaped(): Boolean {
        return hasEscaped
    }

    fun attack() {
        val attack = userPokemon.getAttack()
        val damage = (attack * 2..attack * 8).random()
        val multiplier = userPokemon.getAttackMultiplier(opponentPokemon)
        opponentPokemon.decreaseHP((damage * multiplier).toInt())
        turn++
    }

    fun opponentPlay() {
        tryToEscapeAsOpponent()
        if (hasEscaped) return
        opponentAttack()
    }

    private fun opponentAttack() {
        val attack = opponentPokemon.getAttack()
        val damage = (attack * 5..attack * 15).random()
        val multiplier = opponentPokemon.getAttackMultiplier(userPokemon)
        userPokemon.decreaseHP((damage * multiplier).toInt())
        turn++
    }

    private fun tryToEscapeAsOpponent() {
        if (turn < 2) return
        hasEscaped = (0..4).random() == 0
    }

    fun tryToCapture(inventoryItemType: InventoryItemType): Boolean {
        if (inventoryItemType.getName() == INVENTORY_ITEM.masterball.name) return true
        val captureRate = 0.5 * opponentPokemon.getCurrentHP() * (1 - userPokemon.getCurrentHP() / userPokemon.getMaxHP())
        return (0..100).random() < captureRate
    }

    fun isOver(): Boolean {
        return userPokemon.getCurrentHP() <= 0 || opponentPokemon.getCurrentHP() <= 0 || hasEscaped
    }

    fun isUserWinner(): Boolean {
        return opponentPokemon.getCurrentHP() <= 0
    }
}