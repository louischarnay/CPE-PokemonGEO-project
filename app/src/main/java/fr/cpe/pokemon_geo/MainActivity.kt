package fr.cpe.pokemon_geo

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.ui.layout.BottomNavigationBar
import fr.cpe.pokemon_geo.ui.navigation.AppNavigation
import fr.cpe.pokemon_geo.ui.screen.starter.Starter
import fr.cpe.pokemon_geo.ui.theme.PokemongeoTheme
import fr.cpe.pokemon_geo.usecase.GeneratePokemonsUseCase
import fr.cpe.pokemon_geo.utils.hasLocationPermission
import fr.cpe.pokemon_geo.utils.loadPokemonsFromResources
import kotlinx.coroutines.launch
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

        lifecycleScope.launch {
            val showStarterPage = shouldShowStarterPage()

            setContent {
                val navController = rememberNavController()

                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )

                PokemongeoTheme(darkTheme = false) {
                    if (showStarterPage) {
                        Starter(pokemons = pokemons, navController = navController)
                    } else {
                        Scaffold(
                            bottomBar = { BottomNavigationBar(navController = navController) }
                        ) { padding ->
                            LaunchedEffect(!hasLocationPermission()) {
                                permissionState.launchMultiplePermissionRequest()
                            }
                            AppNavigation(
                                navController = navController,
                                pokemons = pokemons,
                                modifier = Modifier.padding(padding)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initializeMap() {
        val mapInstance = Configuration.getInstance()
        mapInstance.load(this, PreferenceManager.getDefaultSharedPreferences(this))
        mapInstance.userAgentValue = this.packageName
    }

    private suspend fun shouldShowStarterPage(): Boolean {
        return repository.getAllUserPokemon().isEmpty()
    }
}
