package fr.cpe.pokemon_geo.model.fight

import fr.cpe.pokemon_geo.model.pokemon.Pokemon

class PokemonFighter(
    order: Int,
    name: String,
    imageName: String,
    type1: String,
    type2: String?,
    currentHP: Int,
) : Pokemon(order, name, imageName, type1, type2) {

    private var currentHP: Int = currentHP
        set(value) {
            field = value
            if (field < 0)  field = 0
        }

    fun getCurrentHP(): Int {
        return currentHP
    }

    fun increaseHP(amount: Int) {
        currentHP += amount
    }

    fun decreaseHP(amount: Int) {
        currentHP -= amount
    }
}