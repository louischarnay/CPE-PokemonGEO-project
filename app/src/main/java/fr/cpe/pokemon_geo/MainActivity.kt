package fr.cpe.pokemon_geo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.ui.layout.BottomNavigationBar
import fr.cpe.pokemon_geo.ui.layout.shouldShowBottomNavigation
import fr.cpe.pokemon_geo.ui.navigation.AppNavigation
import fr.cpe.pokemon_geo.ui.navigation.Screen
import fr.cpe.pokemon_geo.ui.theme.PokemongeoTheme
import fr.cpe.pokemon_geo.usecase.GeneratePokemonsUseCase
import fr.cpe.pokemon_geo.utils.LOCATION_PERMISSIONS
import fr.cpe.pokemon_geo.utils.hasLocationPermission
import fr.cpe.pokemon_geo.utils.loadPokemonsFromResources
import kotlinx.coroutines.runBlocking
import org.osmdroid.config.Configuration
import javax.inject.Inject


@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var generatePokemonsUseCase: GeneratePokemonsUseCase

    @Inject
    lateinit var repository: PokemonGeoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeMap()
        val pokemons = loadPokemonsFromResources(resources.openRawResource(R.raw.pokemons))

        setContent {
            val navController = rememberNavController()
            val permissionState = rememberMultiplePermissionsState(permissions = LOCATION_PERMISSIONS)

            val snackbarHostState = remember { SnackbarHostState() }

            var startDestination by remember { mutableStateOf<Screen?>(null) }

            PokemongeoTheme(darkTheme = false) {

                LaunchedEffect(Unit) {
                    startDestination = runBlocking { getStartDestination() }
                }

                LaunchedEffect(!hasLocationPermission()) {
                    permissionState.launchMultiplePermissionRequest()
                }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = {
                        val currentRoute = currentRoute(navController)
                        if (shouldShowBottomNavigation(currentRoute)) {
                            BottomNavigationBar(navController = navController)
                        }
                    },
                    modifier = Modifier.zIndex(1f)
                ) { padding ->
                    AppNavigation(
                        navController = navController,
                        startDestination = startDestination ?: Screen.Profile,
                        pokemons = pokemons,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }

    private fun initializeMap() {
        val mapInstance = Configuration.getInstance()
        mapInstance.load(this, PreferenceManager.getDefaultSharedPreferences(this))
        mapInstance.userAgentValue = this.packageName
    }

    private suspend fun getStartDestination(): Screen {
        val hasProfile = repository.getProfile() != null
        val hasAtLeastOnePokemon = repository.getAllUserPokemon().isNotEmpty()
        return when {
            !hasProfile -> Screen.Welcome
            !hasAtLeastOnePokemon -> Screen.Starter
            else -> Screen.Profile
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
