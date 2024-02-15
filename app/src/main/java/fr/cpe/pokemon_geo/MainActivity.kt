package fr.cpe.pokemon_geo

import android.Manifest
import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.cpe.pokemon_geo.service.location.LocationService
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.cpe.pokemon_geo.ui.layout.BottomNavigationBar
import fr.cpe.pokemon_geo.ui.navigation.AppNavigation
import fr.cpe.pokemon_geo.ui.theme.PokemongeoTheme
import fr.cpe.pokemon_geo.utils.hasLocationPermission

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startLocationService()
            } else {
                Toast.makeText(this, getString(R.string.location_permission_not_present),
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (hasLocationPermission()) startLocationService()
        else requestLocationPermissions()

        setContent {
            val navController = rememberNavController()

            PokemongeoTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { _ ->
                    AppNavigation(navController = navController)
                }
            }
        }
    }

    override fun onDestroy() {
        stopLocationService()
        super.onDestroy()
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

    private fun requestLocationPermissions() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemongeoTheme {}
}