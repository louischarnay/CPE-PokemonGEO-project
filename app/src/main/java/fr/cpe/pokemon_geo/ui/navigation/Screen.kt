package fr.cpe.pokemon_geo.ui.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Starter : Screen("starter")
    object Profile : Screen("profile")
    object Pokedex : Screen("pokedex")
    object Map : Screen("map")
    object UserInventory : Screen("user_inventory")
    object UserPokemon : Screen("user_pokemon")
    object PokemonFighterChoice : Screen("pokemon_fighter_choice")
    object Fight : Screen("fight")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}