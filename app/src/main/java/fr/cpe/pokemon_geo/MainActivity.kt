package fr.cpe.pokemon_geo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import fr.cpe.pokemon_geo.ui.layout.BottomNavigationBar
import fr.cpe.pokemon_geo.ui.navigation.AppNavigation
import fr.cpe.pokemon_geo.ui.screen.map.OsmdroidMapViewModel
import fr.cpe.pokemon_geo.ui.screen.starter.Welcome
import fr.cpe.pokemon_geo.ui.screen.starter.WelcomeViewModel
import fr.cpe.pokemon_geo.ui.theme.PokemongeoTheme
import fr.cpe.pokemon_geo.usecase.GeneratePokemonsUseCase
import fr.cpe.pokemon_geo.utils.LOCATION_PERMISSIONS
import fr.cpe.pokemon_geo.utils.hasLocationPermission
import fr.cpe.pokemon_geo.utils.loadPokemonsFromResources
import org.osmdroid.config.Configuration
import javax.inject.Inject


@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var generatePokemonsUseCase: GeneratePokemonsUseCase

    private val welcomeViewModel: WelcomeViewModel by viewModels()
    private val osmdroidMapViewModel: OsmdroidMapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeMap()
        val pokemons = loadPokemonsFromResources(resources.openRawResource(R.raw.pokemons))
        osmdroidMapViewModel.fetchMapDataPeriodically(pokemons)

        setContent {
            val navController = rememberNavController()
            val permissionState = rememberMultiplePermissionsState(permissions = LOCATION_PERMISSIONS)

            PokemongeoTheme(darkTheme = false) {

                LaunchedEffect(!hasLocationPermission()) {
                    permissionState.launchMultiplePermissionRequest()
                }

                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) },
                    modifier = Modifier.zIndex(1f)
                ) { padding ->
                    AppNavigation(
                        navController = navController,
                        pokemons = pokemons,
                        modifier = Modifier.padding(padding),
                        osmdroidMapViewModel = osmdroidMapViewModel,
                    )
                }

                val showWelcomeScreen by welcomeViewModel.showWelcomeSreen.collectAsState()
                if (showWelcomeScreen) {
                    Welcome(pokemons = pokemons, welcomeViewModel = welcomeViewModel)
                }
            }
        }
    }

    private fun initializeMap() {
        val mapInstance = Configuration.getInstance()
        mapInstance.load(this, PreferenceManager.getDefaultSharedPreferences(this))
        mapInstance.userAgentValue = this.packageName
    }
}
