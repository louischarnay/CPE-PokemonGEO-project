package fr.cpe.pokemon_geo.ui.navigation

sealed class Screen(open val route: String) {

    companion object {
        const val ARG_USER_POKEMON_ID = "userPokemonId"
        const val ARG_OPPONENT_POKEMON_ID = "opponentPokemonId"
        const val ARG_POKE_BALL = "pokeBall"
    }

    data object Welcome : Screen("welcome")
    data object Starter : Screen("starter")
    data object Profile : Screen("profile")
    data object Pokedex : Screen("pokedex")
    data object Map : Screen("map")
    data object UserInventory : Screen("user_inventory")
    data object UserPokemon : Screen("user_pokemon")
    data object PokemonFighterChoice : Screen("pokemon_fighter_choice") {
        override val route: String get() = super.route + "/{$ARG_OPPONENT_POKEMON_ID}"
        fun withArgs(vararg args: String) = super.withArgs(super.route, *args)
    }
    data object Fight : Screen("fight") {
        override val route: String get() = super.route + "/{$ARG_USER_POKEMON_ID}" + "/{$ARG_OPPONENT_POKEMON_ID}"
        fun withArgs(vararg args: String) = super.withArgs(super.route, *args)
    }
    data object FightCapture : Screen("fight_capture")

    protected fun withArgs(route: String, vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}