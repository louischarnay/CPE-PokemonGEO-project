package fr.cpe.pokemon_geo

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint
import fr.cpe.pokemon_geo.ui.layout.BottomNavigationBar
import fr.cpe.pokemon_geo.ui.navigation.AppNavigation
import fr.cpe.pokemon_geo.ui.theme.PokemongeoTheme
import fr.cpe.pokemon_geo.utils.hasLocationPermission
import org.osmdroid.config.Configuration


@OptIn(ExperimentalPermissionsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeMap()

        setContent {
            val navController = rememberNavController()

            val permissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )

            PokemongeoTheme(darkTheme = false) {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { padding ->
                    LaunchedEffect(!hasLocationPermission()) {
                        permissionState.launchMultiplePermissionRequest()
                    }
                    AppNavigation(navController = navController, modifier = Modifier.padding(padding))
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
