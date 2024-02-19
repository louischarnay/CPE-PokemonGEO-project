package fr.cpe.pokemon_geo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import fr.cpe.pokemon_geo.service.location.LocationService
import fr.cpe.pokemon_geo.ui.layout.BottomNavigationBar
import fr.cpe.pokemon_geo.ui.navigation.AppNavigation
import fr.cpe.pokemon_geo.ui.theme.PokemongeoTheme
import fr.cpe.pokemon_geo.utils.hasLocationPermission
import fr.cpe.pokemon_geo.utils.showToast
import org.osmdroid.config.Configuration


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startLocationService()
            } else {
                showToast(this, getString(R.string.location_permission_not_present))
                finish()
            }
        }


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeLocationService()
        initializeMap()

        setContent {
            val navController = rememberNavController()

            PokemongeoTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { padding ->
                    AppNavigation(navController = navController, modifier = Modifier.padding(padding))
                }
            }
        }
    }

    override fun onDestroy() {
        stopLocationService()
        super.onDestroy()
    }

    private fun initializeMap() {
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        Configuration.getInstance().userAgentValue = this.packageName
    }

    private fun initializeLocationService() {
        if (hasLocationPermission()) startLocationService()
        else requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun startLocationService() {
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }

    private fun stopLocationService() {
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_STOP
            startService(this)
        }
    }
}
