package fr.cpe.pokemon_geo.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import fr.cpe.pokemon_geo.ui.screen.fight.Fight
import fr.cpe.pokemon_geo.ui.screen.map.OsmdroidMap
import fr.cpe.pokemon_geo.ui.screen.pokedex.Pokedex
import fr.cpe.pokemon_geo.ui.screen.pokemon_fighter.PokemonFighterChoice
import fr.cpe.pokemon_geo.ui.screen.profile.Profile
import fr.cpe.pokemon_geo.ui.screen.starter.Starter
import fr.cpe.pokemon_geo.ui.screen.user_inventory.UserInventory
import fr.cpe.pokemon_geo.ui.screen.user_pokemon.UserPokemons
import fr.cpe.pokemon_geo.ui.screen.welcome.Welcome

@Composable
fun AppNavigation(
    navController: NavController,
    startDestination: Screen,
    pokemons: List<Pokemon>,
    modifier: Modifier,
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        composable(route = Screen.Profile.route) {
            BackHandler(true) {}
            Profile(navController)
        }

        composable(route = Screen.Pokedex.route) {
            BackHandler(true) {}
            Pokedex(pokemons)
        }

        composable(route = Screen.Map.route) {
            BackHandler(true) {}
            OsmdroidMap(navController, pokemons)
        }

        composable(route = Screen.Welcome.route) {
            Welcome(navController)
        }

        composable(route = Screen.Starter.route) {
            Starter(pokemons, navController)
        }

        composable(route = Screen.UserInventory.route) {
            UserInventory()
        }

        composable(route = Screen.UserPokemon.route) {
            UserPokemons()
        }

        composable(
            route = Screen.PokemonFighterChoice.route,
            arguments = listOf(
                navArgument(Screen.ARG_OPPONENT_POKEMON_ID) {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            val opponentPokemonId = entry.arguments?.getInt(Screen.ARG_OPPONENT_POKEMON_ID)
            if (opponentPokemonId == null) {
                navController.popBackStack()
                return@composable
            }
            PokemonFighterChoice(navController, opponentPokemonId)
        }

        composable(
            route = Screen.Fight.route,
            arguments = listOf(
                navArgument(Screen.ARG_USER_POKEMON_ID) {
                    type = NavType.IntType
                    nullable = false
                },
                navArgument(Screen.ARG_OPPONENT_POKEMON_ID) {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            val userPokemonId = entry.arguments?.getInt(Screen.ARG_USER_POKEMON_ID)
            val opponentPokemonId = entry.arguments?.getInt(Screen.ARG_OPPONENT_POKEMON_ID)
            val pokeBallName = entry.savedStateHandle.get<String>(Screen.ARG_POKE_BALL)

            if (opponentPokemonId == null || userPokemonId == null) {
                navController.popBackStack()
                return@composable
            }
            Fight(navController, userPokemonId, opponentPokemonId, pokeBallName)
        }

        composable(route = Screen.FightCapture.route) {
            UserInventory(
                onClick = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(Screen.ARG_POKE_BALL, it.getType().getName())
                    navController.popBackStack()
                }
            )
        }
    }
}